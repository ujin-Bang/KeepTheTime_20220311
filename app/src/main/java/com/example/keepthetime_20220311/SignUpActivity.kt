package com.example.keepthetime_20220311

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20220311.databinding.ActivitySignInBinding
import com.example.keepthetime_20220311.databinding.ActivitySignUpBinding
import com.example.keepthetime_20220311.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

      binding.btnSignup.setOnClickListener {

          val inputEamil = binding.edtEmail.text.toString()
          val inputPw = binding.edtPssword.text.toString()
          val inputNickname = binding.edtNickname.text.toString()

//          회원가입 API호출(PUT -"/user")

          apiList.putRequestSignup(inputEamil, inputPw, inputNickname).enqueue( object : Callback<BasicResponse> {
              override fun onResponse(
                  call: Call<BasicResponse>,
                  response: Response<BasicResponse>
              ) {

                  if(response.isSuccessful){

                      Toast.makeText(mContext, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
                        finish()
                  }

              }

              override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

              }

          })
      }

    }

    override fun setValues() {

    }
}