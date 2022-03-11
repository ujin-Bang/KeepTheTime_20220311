package com.example.keepthetime_20220311

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20220311.api.APIList
import com.example.keepthetime_20220311.api.ServerAPI
import com.example.keepthetime_20220311.databinding.ActivitySignInBinding
import com.example.keepthetime_20220311.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : BaseActivity() {

    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnLogin.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPssword.text.toString()

            apiList.postRequestLogin(inputEmail, inputPassword).enqueue(object :Callback<BasicResponse>{
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                    Log.d("응답확인",response.toString())

//                   Retrofit라이브러리의 response는 성공/실패 여부에 따라 다른 본문을 봐야함

//                    성공인지?
                    if(response.isSuccessful){
//                        모든 결과가 최종 성공인 경우(code = 200으로 내려온 경우)
//                        response.body() 이용

                       val br = response.body()!! // 성공시 무조건 본문이 있다.=>BasicResponse형태의 변수로 파싱되어 나옴.

//                        Retrofit의 Callback은 UIThread 안으로 다시 돌아오도록 처리되어있다.
//                        UI 조작을 위해 rundOnUiThread{}작성 필요 없음
                        Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()
                    }
//                    실패인지?
                    else {
//                        서버 연결은 되었는데 로직만 실패(로그인 비번틀림 등)
//                        response.errorBody()활용
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//                    서버에 물리적 연결 실패
                }


            })

        }


    }

    override fun setValues() {

    }
}