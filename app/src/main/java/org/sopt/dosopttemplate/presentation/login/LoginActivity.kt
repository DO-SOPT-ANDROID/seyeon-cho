package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

        initLoginBtnListener()
        initSignUpBtnListener()
        observeLoginResult()
    }

    private fun initLoginBtnListener() {
        binding.loginButton.setOnClickListener {
            val id = binding.inputTextId.text.toString()
            val password = binding.inputTextPw.text.toString()

            loginViewModel.login(
                id = id,
                password = password,
            )
        }
    }

    private fun initSignUpBtnListener() {
        binding.signupButton.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }
    }

    private fun observeLoginResult() {
        loginViewModel.uiState.flowWithLifecycle(lifecycle).onEach { loginState ->
            when (loginState) {
                is UiState.Success -> {
                    Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                }

                is UiState.Error -> {
                    Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }

                is UiState.Loading -> {
                    Toast.makeText(this@LoginActivity, "로그인 중", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}