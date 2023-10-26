package org.sopt.dosopttemplate
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var enteredId: String
    lateinit var enteredPassword: String
    lateinit var enteredMajor: String
    lateinit var enteredName: String
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signButton = binding.signupButton
        val loginButton = binding.loginButton

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                enteredId = data?.getStringExtra("entered_id") ?: ""
                enteredPassword = data?.getStringExtra("entered_password") ?: ""
                enteredMajor = data?.getStringExtra("entered_Major") ?: ""
                enteredName = data?.getStringExtra("entered_Name") ?: ""
            }
        }

        signButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }

        loginButton.setOnClickListener {

            val inputId = binding.inputTextId
            val inputPw = binding.inputTextPw

                val isLoginSuccessful =
                    enteredId == inputId.text.toString() && enteredPassword == inputPw.text.toString()
                if (isLoginSuccessful) {
                    Toast.makeText(this, "로그인에 성공했습니다 :)", Toast.LENGTH_LONG).show()

                    val mainIntent = Intent(this, HomeActivity::class.java)
                    mainIntent.putExtra("user_id", enteredId)
                    mainIntent.putExtra("user_major", enteredMajor)
                    startActivity(mainIntent)
                } else {
                    Toast.makeText(this, "로그인에 실패했습니다 :(", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

