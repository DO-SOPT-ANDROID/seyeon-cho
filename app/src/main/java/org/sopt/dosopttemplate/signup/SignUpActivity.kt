package org.sopt.dosopttemplate.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.data.api.ServicePool
import org.sopt.dosopttemplate.data.signup.RequestSignupDto
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response

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
                ServicePool.authService.signup(RequestSignupDto(enteredId, enteredPassword, enteredMajor, enteredName))
                    .enqueue(object : retrofit2.Callback<Unit> {
                    override fun onResponse(
                        call: Call<Unit>,
                        response: Response<Unit>
                    ) {
                        if (response.isSuccessful) {
                            val resultIntent = Intent()
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@SignUpActivity,
                                "회원가입 실패요",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Toast.makeText(
                            this@SignUpActivity,
                            "네트워크 오류요",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
    }
    private fun isValidSignUp(
        enteredId: String,
        enteredPassword: String,
        enteredMajor: String,
        enteredName: String
    ): Boolean {
        val isIdValid = enteredId.length in 6..10
        val isPasswordValid = enteredPassword.length in 8..12
        val isNicknameValid = enteredName.isNotBlank()
        val isMajorValid = enteredMajor.isNotBlank()

        return isIdValid && isPasswordValid && isNicknameValid && isMajorValid
    }
}
