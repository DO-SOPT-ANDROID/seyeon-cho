package org.sopt.dosopttemplate.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.api.ServicePool
import org.sopt.dosopttemplate.data.signup.RequestSignupDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {
    private val _signupSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val signupSuccess: MutableLiveData<Boolean> = _signupSuccess

    fun signup(id: String, password: String, major: String, name: String) {
        ServicePool.authService.signup(RequestSignupDto(id, password, major, name))
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        signupSuccess.value = true
                    } else {
                        signupSuccess.value = false
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    //에러 로직
                }
            })
    }
}
