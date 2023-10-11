package org.sopt.dosopttemplate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.LoginActivity
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signupButton = binding.signupButton
        val editTextId = binding.editTextId
        val editTextPassword = binding.editTextPassword
        val editTextMajor = binding.editTextMajor
        val editTextName = binding.editTextName

        signupButton.setOnClickListener {
            val enteredId = editTextId.text.toString()
            val enteredPassword = editTextPassword.text.toString()
            val enteredMajor = editTextMajor.text.toString()
            val enteredName = editTextName.text.toString()

            if (isValidSignUp(enteredId, enteredPassword, enteredMajor, enteredName)) {
                val loginIntent = Intent(this, LoginActivity::class.java)
                loginIntent.putExtra("entered_id", enteredId)
                loginIntent.putExtra("entered_password", enteredPassword)
                loginIntent.putExtra("entered_Major", enteredMajor)
                loginIntent.putExtra("entered_Name", enteredName)

                startActivity(loginIntent)
            } else {
                Toast.makeText(this, "회원가입 조건을 다시 확인하세요!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun isValidSignUp(
        enteredId: String,
        enteredPassword: String,
        enteredMajor: String,
        enteredName: String
    ): Boolean {

        val isIdValid = enteredId.length >= 6 && enteredId.length <= 10
        val isPasswordValid = enteredPassword.length >= 8 && enteredPassword.length <= 12
        val isNicknameValid = enteredName.length >= 1 && !enteredName.isBlank()
        val isMajorValid = enteredMajor.length >= 1 && !enteredMajor.isBlank()

        return isIdValid && isPasswordValid && isNicknameValid && isMajorValid
    }}
