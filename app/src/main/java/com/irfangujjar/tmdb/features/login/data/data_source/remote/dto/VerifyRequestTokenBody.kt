package com.irfangujjar.tmdb.features.login.data.data_source.remote.dto

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class VerifyRequestTokenBody(
    @SerializedName(JsonKeyNames.REQUEST_TOKEN) val requestToken: String,
    val username: String,
    val password: String
)

//export interface VerifyRequestTokenBodyModel{
//    request_token:string,
//    username:string,
//    password:string
//}
