package org.sopt.dosopttemplate.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.request.RequestLoginDto
import org.sopt.dosopttemplate.data.model.response.ResponseLoginDto
import org.sopt.dosopttemplate.di.ServicePool.authService

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<ResponseLoginDto>>(UiState.Loading)
    val uiState: StateFlow<UiState<ResponseLoginDto>> = _uiState.asStateFlow()

    fun login(id: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authService.postLogin(RequestLoginDto(id, password)).execute()

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _uiState.value = UiState.Success(responseBody)
                    } else {
                        _uiState.value = UiState.Error
                    }
                } else {
                    _uiState.value = UiState.Error
                }
            } catch (e: Exception) {
                Log.d("LoginViewModel","실패")
                _uiState.value = UiState.Error
            }
        }
    }
}


