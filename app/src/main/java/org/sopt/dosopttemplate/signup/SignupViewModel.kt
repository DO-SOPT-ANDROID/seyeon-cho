package org.sopt.dosopttemplate.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import org.sopt.dosopttemplate.data.api.ServicePool
import org.sopt.dosopttemplate.data.signup.RequestSignupDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignupViewModel : ViewModel() {
    private val _signupSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val signupSuccess = _signupSuccess

    private val _isSignupBtn = MutableLiveData(false)
    val isSignupBtn = _isSignupBtn

    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val major = MutableLiveData("")
    val name = MutableLiveData("")

//    val isIdValid = id.map { ID_PATTERN.matcher(it).matches() }
//    val isPwValid = password.map { PW_PATTERN.matcher(it).matches() }
//
//    fun checkBtn() {
//        _isSignupBtn.value = isIdValid.value == true && isPwValid.value == true
//    }

    fun signup(id: String, password: String, major: String, name: String) {
        ServicePool.authService.signup(RequestSignupDto(id, password, major, name))
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    _signupSuccess.value = response.isSuccessful
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    // 에러 로직
                }
            })
    }
    companion object {
        private const val ID_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,10}\$"
        private const val PW_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[^\\w\\s])[a-zA-Z\\d\\S]{6,12}\$"
        val ID_PATTERN: Pattern = Pattern.compile(ID_REGEX)
        val PW_PATTERN: Pattern = Pattern.compile(PW_REGEX)
    }
}
