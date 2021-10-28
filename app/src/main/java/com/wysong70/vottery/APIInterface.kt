package com.wysong70.vottery

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LogInAPI {
    @FormUrlEncoded
    @POST(LOGIN_CHECK_PAGE)
    fun getUser(
        @Field("UseriD") e_Mail: String,
        @Field("EncodedPassWD") ecoded_PW: String
    ): Call<UserList>
}

interface ReadKeyAPI {
    @FormUrlEncoded
    @POST(GET_RSAKEY_PAGE)
    fun getPublickKey(
        @Field("null") data: String
    ): Call<RSAKey>
}