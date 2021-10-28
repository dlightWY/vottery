package com.wysong70.vottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.wysong70.vottery.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.Key

class MainActivity : AppCompatActivity() {
    val retrifitreq by lazy { RetrifitRequest() }
    val rsa by lazy { RSAClass() }
    val mABinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mABinding.root)


        GlobalScope.launch {

            retrifitreq.getRSAKey(this@MainActivity)
            while (pubKeyString == "pKey") {
                delay(100)
            }
            val rsaKey: Key? = rsa.PEMtoKey(pubKeyString)

            mABinding.btLogIn.setOnClickListener {

                if (rsaKey != null) {
                    val encrypedPW: String = rsa.encrypt(mABinding.edPw.text.toString(), rsaKey)

                    retrifitreq.getUserInfo(mABinding.edId.text.toString(), encrypedPW, this@MainActivity)
                }
            }
            while (statusInfomation.status == "Start") {
                delay(100)
            }
            when(statusInfomation.source) {
                1 -> {
                    val intent = Intent(this@MainActivity, VotteryMainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}