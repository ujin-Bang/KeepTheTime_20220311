package com.example.keepthetime_20220311

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20220311.databinding.ActivitySignInBinding
import com.example.keepthetime_20220311.databinding.ActivitySignUpBinding

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
      }

    }

    override fun setValues() {

    }
}