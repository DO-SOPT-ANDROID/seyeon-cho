package org.sopt.dosopttemplate.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.api.ServicePool
import org.sopt.dosopttemplate.data.api.ServicePool.authService
import org.sopt.dosopttemplate.data.login.RequestLoginDto
import org.sopt.dosopttemplate.data.login.ResponseLoginDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState<ResponseLoginDto>>(LoginState.Loading)
    val loginState: StateFlow<LoginState<ResponseLoginDto>> = _loginState.asStateFlow()

    fun login(id: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authService.login(RequestLoginDto(id, password)).execute()

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _loginState.value = LoginState.Success(responseBody)
                    } else {
                        _loginState.value = LoginState.Error
                    }
                } else {
                    _loginState.value = LoginState.Error
                }
            } catch (e: Exception) {
                Log.d("LoginViewModel", "실패")
                _loginState.value = LoginState.Error
            }
        }
    }
}


