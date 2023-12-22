package org.sopt.dosopttemplate.login


sealed class LoginState<out T> {
    object Loading : LoginState<Nothing>()
    data class Success<T>(val data: T) : LoginState<T>()
    object Error : LoginState<Nothing>()
}
