package org.sopt.dosopttemplate.login
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.Home.HomeActivity
import org.sopt.dosopttemplate.signup.SignUpActivity
import org.sopt.dosopttemplate.data.api.AuthService
import org.sopt.dosopttemplate.data.login.RequestLoginDto
import org.sopt.dosopttemplate.data.login.ResponseLoginDto
import org.sopt.dosopttemplate.data.api.ServicePool
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authService: AuthService
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

            val id = binding.inputTextId.text.toString()
            val password = binding.inputTextPw.text.toString()

            ServicePool.authService.login(RequestLoginDto(id, password))
                .enqueue(object : retrofit2.Callback<ResponseLoginDto> {
                    override fun onResponse(
                        call: Call<ResponseLoginDto>,
                        response: Response<ResponseLoginDto>,
                    ) {
                        if (response.isSuccessful) {
                            val data: ResponseLoginDto = response.body()!!
                            val userId = data.id
                            Toast.makeText(
                                this@LoginActivity,
                                "로그인 성공, 유저의 ID $userId ",
                                Toast.LENGTH_SHORT,
                            ).show()

                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "서버 에러 발생", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}

