package org.sopt.dosopttemplate.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signupViewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.signupViewModel = signupViewModel

        clicksignbtn()
        observeSignupResult()
        //observeSignupValid()
    }

    private fun clicksignbtn() {
        binding.signupButton.setOnClickListener {
            val id = binding.editTextId.text.toString()
            val password = binding.editTextPassword.text.toString()
            val major = binding.editTextMajor.text.toString()
            val name = binding.editTextName.text.toString()

            signupViewModel.signup(id, password, major, name)
        }
    }

    private fun observeSignupResult() {
        signupViewModel.signupSuccess.observe(this) {
            if (it) {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            } else {
                Toast.makeText(this@SignUpActivity, "회원가입 실패요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

