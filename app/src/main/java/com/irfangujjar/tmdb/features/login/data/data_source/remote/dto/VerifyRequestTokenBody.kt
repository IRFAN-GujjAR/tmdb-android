package com.irfangujjar.tmdb.features.login.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName

data class VerifyRequestTokenBody(
    @SerializedName("request_token") val requestToken: String,
    val username: String,
    val password: String
)

//export interface VerifyRequestTokenBodyModel{
//    request_token:string,
//    username:string,
//    password:string
//}
