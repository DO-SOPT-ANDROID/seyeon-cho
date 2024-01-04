package org.sopt.dosopttemplate.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.model.request.RequestSignupDto
import org.sopt.dosopttemplate.di.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {
    private val _signupSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val signupSuccess = _signupSuccess

    private val _isSignupBtn = MutableLiveData(false)
    val isSignupBtn = _isSignupBtn

    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val major = MutableLiveData("")
    val name = MutableLiveData("")

    fun signup(id: String, password: String, major: String, name: String) {
        ServicePool.authService.postSignUp(RequestSignupDto(id, password, major, name))
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    _signupSuccess.value = response.isSuccessful
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                }
            })
    }
}
