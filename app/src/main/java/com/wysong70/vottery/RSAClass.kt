package com.wysong70.vottery

import android.util.Base64
import java.security.Key
import java.security.KeyFactory
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

class RSAClass {
    fun PEMtoKey(keyStr : String): Key?{
        return try{
            val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")
            var keyString = keyStr
            var tmpKey: Key? = null;

            if (keyString.contains("PUBLIC KEY") ) {
                keyString = keyString.replace("-----BEGIN PUBLIC KEY-----", "")
                keyString = keyString.replace("-----END PUBLIC KEY-----", "")
                val spec = X509EncodedKeySpec(Base64.decode(keyString, Base64.DEFAULT))
                tmpKey = keyFactory.generatePublic(spec)
            } else if (keyStr.contains("PRIVATE KEY")) {
                keyString = keyString.replace("-----BEGIN PRIVATE KEY-----", "")
                keyString = keyString.replace("-----END PRIVATE KEY-----", "")
                val spec = PKCS8EncodedKeySpec(Base64.decode(keyStr, Base64.DEFAULT))
                tmpKey = keyFactory.generatePrivate(spec)
            }
            tmpKey
        }  catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            null
        } catch (e: InvalidKeySpecException) {
            e.printStackTrace()
            null
        }
    }

    fun encrypt(input:String, key: Key ) : String{

        val encCipher = Cipher.getInstance("RSA/None/PKCS1Padding")
        encCipher.init(Cipher.ENCRYPT_MODE,  key)
        val encryptTextByteArray = encCipher.doFinal(input.toByteArray())
        val rtnString = String(Base64.encode(encryptTextByteArray, Base64.DEFAULT))

        return rtnString
    }
}