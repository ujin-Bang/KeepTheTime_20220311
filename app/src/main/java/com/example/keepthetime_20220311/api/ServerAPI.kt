package com.example.keepthetime_20220311.api

import android.content.Context
import com.example.keepthetime_20220311.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

//    Retrofit 클래스 객체화 함수만들기 => 단일 객체만 만들어서 모든화면에서 공유
//    여러개의 객체를 만들 필요가 없다. SingleTon 패턴이라고 함.

    companion object {

        //        서버 통신 담당 클래스 : 레트로핏 클래스 객체를 담을 변수
//        아직 안 만들었다면? 새로 만들고 , 만들어둔게 있다면? 있는 retrofit 재활용
        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://keepthetime.xyz"

        fun getRetrofit(context: Context): Retrofit {

//            Retrofit 라이브러리는 클래스 차원에서 BASE_URL을 설정할 수 있게 도와줌
//            Retrofit + Gson 두개의 라이브러리를 결합하면 => JSON 파싱이 쉬어짐.


            if (retrofit == null) {

                //      retrofit 객체 생성시에 추가 세팅

                // 자동으로 토큰을 첨부하도록
//              retrofit 변수를 통해서 API통신을 시작하기 직전에 통신 정보를 먼저 가로챈다.
//              가로챈 통신 정보에서 무조건 헤더에 토큰을 첨부해두고, 나머지 작업을 이어가도록.

                val interceptor = Interceptor{
                    with(it) {

                        // 기존의 request에 헤더를 추가해 주자

                        val newRequest = request().newBuilder()
                            .addHeader("X-Http_Token", ContextUtil.getLoginUserToken(context))
                            .build()

                        // 다시 하려던 일을 이어가도록
                        proceed(newRequest)

                    }
                }

                // 만들어낸 인터셉터를 활용하도록 세팅
//                레트로핏이 사용하는 클라이언트 객체를 수정

                val myClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL) // 어느 서버를 기반으로 움직일건지 설정.
                    .addConverterFactory(GsonConverterFactory.create())// gson 라이브러리와 결합
                    .client(myClient) //인터셉터를 부착해둔 클라이언트로 통신하도록.
                    .build()
            }

            return retrofit!!

        }
    }
}