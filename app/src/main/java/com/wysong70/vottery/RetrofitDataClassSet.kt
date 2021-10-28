package com.wysong70.vottery

import android.os.Parcelable
import retrofit2.http.Field

//data class UserListPost (
//    var UseriD: String,
//    var EncodedPassWD: String
//)

data class UserList (
    var status: String,
    var dataSet: List<User>
)


data class User(
    var login_id:String?,
    var full_name:String?,
    var nick_name:String?,
    var e_mail:String?,
    var phone_no:String?,
    var age:Int,
    var last_access:String?,
    var gender:String?,
    var coins:Int
)

data class RSAKey(
    var status: String?,
    var public_key: String?
)

data class StatusInfo(
    var status: String?,
    var source: Int
)
