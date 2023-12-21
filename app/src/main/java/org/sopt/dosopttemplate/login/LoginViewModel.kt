package org.sopt.dosopttemplate.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.api.ServicePool
import org.sopt.dosopttemplate.data.login.RequestLoginDto
import org.sopt.dosopttemplate.data.login.ResponseLoginDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {
    private val _loginResult: MutableLiveData<ResponseLoginDto> = MutableLiveData()
    val loginResult: MutableLiveData<ResponseLoginDto> = _loginResult

    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: MutableLiveData<Boolean> = _loginSuccess

    fun login(id: String, password: String) {
            ServicePool.authService.login(RequestLoginDto(id, password))
                .enqueue(object : Callback<ResponseLoginDto> {
                    override fun onResponse(
                        call: Call<ResponseLoginDto>,
                        response: Response<ResponseLoginDto>,
                    ) {
                        if (response.isSuccessful) {
                            loginResult.value = response.body()
                            loginSuccess.value = true
                        } else {
                            loginSuccess.value = false
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                        // TODO: 에러 처리 로직
                    }
                })
        }
    }

