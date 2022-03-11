package com.example.keepthetime_20220311.api

import com.example.keepthetime_20220311.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.PUT

interface APIList {

//    BASE_URL에 해당하는 서버에서 어떤 기능들을 사용할건지 명시.

    @FormUrlEncoded // 파라미터중에 Field(formData)에 담아야 하는 파라미터가 있다면 필요한 구문.
    @POST("/user")
    fun postRequestLogin(
        @Field("email")email: String,
        @Field("password") pw: String
    ): Call<BasicResponse> //서버가 주는 응답을(성공시에) BasicResponse형태로 자동 파싱

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignup(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("nick_name") nick: String,
    ) : Call<BasicResponse>
}