package org.sopt.dosopttemplate.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.presentation.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signupViewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.signupViewModel = signupViewModel

        initSignUpBtnListener()
        observeSignupResult()
    }

    private var signUpId: String = ""
    private var signUpPassword: String = ""
    private var signUpMajor: String = ""
    private var signUpName: String = ""
    public fun initSignUpBtnListener() {
        binding.signupButton.setOnClickListener {
            val signUpId = binding.editTextId.text.toString()
            val signUpPassword = binding.editTextPassword.text.toString()
            val signUpMajor = binding.editTextMajor.text.toString()
            val signUpName = binding.editTextName.text.toString()

            signupViewModel.signup(signUpId, signUpPassword, signUpMajor, signUpName)

        }
    }

    private fun observeSignupResult() {
        signupViewModel.signupSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                Intent(this, LoginActivity::class.java).apply {
                    putExtra("ID", signUpId)
                    putExtra("PASSWORD", signUpPassword)
                    putExtra("MAJOR", signUpMajor)
                    putExtra("NAME", signUpName)
                    startActivity(this)
                }
                finish()
            } else {
                Toast.makeText(this@SignUpActivity, getString(R.string.signup_failed), Toast.LENGTH_SHORT).show()

            }
        }
    }
}