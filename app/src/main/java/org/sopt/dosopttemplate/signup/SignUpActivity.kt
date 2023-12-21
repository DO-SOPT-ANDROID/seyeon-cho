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

        signup()
        observeSignupResult()
    }


    private fun signup(){
        binding.signupButton.setOnClickListener {
            val enteredId = binding.editTextId.text.toString()
            val enteredPassword = binding.editTextPassword.text.toString()
            val enteredMajor = binding.editTextMajor.text.toString()
            val enteredName = binding.editTextName.text.toString()

            if (isValidSignUp(enteredId, enteredPassword, enteredMajor, enteredName)) {
                signupViewModel.signup(enteredId, enteredPassword, enteredMajor, enteredName)
            }
        }}
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

    private fun isValidID(id: String): Boolean =
        Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$").matches(id)

    private fun isValidPassword(password: String): Boolean =
        Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#\$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?]).{6,12}$").matches(password)

    private fun isValidSignUp(
        enteredId: String,
        enteredPassword: String,
        enteredMajor: String,
        enteredName: String
    ): Boolean =
        isValidID(enteredId) && isValidPassword(enteredPassword) && enteredName.isNotBlank() && enteredMajor.isNotBlank()}

