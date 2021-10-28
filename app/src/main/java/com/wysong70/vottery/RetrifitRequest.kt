package com.wysong70.vottery

import android.content.ContentValues.TAG
import android.content.Context
import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrifitRequest() {
    val retrofit = RetrofitClient.getInstance()

    fun getUserInfo(e_Mail:String, ecoded_PW:String, context : Context) {

        val server = retrofit.create(LogInAPI::class.java)
        server.getUser(e_Mail, ecoded_PW).enqueue(object : Callback<UserList> {


            override fun onResponse(
                call: Call<UserList>,
                response: Response<UserList>
            ) {
                val ResponseBody: UserList? = response.body()
                //val toastText = context.getText(R.string.IdNotFound)
                if (ResponseBody != null) {
                    when (ResponseBody.status) {
                        "ID Not Found" -> {
                            //Log.d(TAG, "성공1 : ${ResponseBody.toString()}")
                            Toast.makeText(
                                context,
                                context.getString(R.string.IdNotFound),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        "IncorrectPW" -> {
                            Toast.makeText(
                                context,
                                context.getString(R.string.IncorrectPW),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        "Scuccess" -> {
                            userInfo = ResponseBody.dataSet[0]
                            statusInfomation.status = "LogIn"
                            statusInfomation.source = 1
                            Toast.makeText(
                                context,
                                context.getString(R.string.loginSuccess),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                Log.d(TAG, "성공 : ${t.message}")
            }
        })
    }

    fun getRSAKey(context : Context) {
        val server = retrofit.create(ReadKeyAPI::class.java)

        server.getPublickKey("").enqueue(object : Callback<RSAKey> {
            override fun onResponse(
                call: Call<RSAKey>, response: Response<RSAKey>
            ) {
                val responseBody = response.body()
                if (responseBody != null) {
                    pubKeyString = responseBody.public_key.toString()
                }
            }

            override fun onFailure(call: Call<RSAKey>, t: Throwable) {
                Toast.makeText(context, "서버 연결 실패 입니다. 잠시후 다시 시도해 주세요", Toast.LENGTH_LONG).show()
            }
        })
    }

}