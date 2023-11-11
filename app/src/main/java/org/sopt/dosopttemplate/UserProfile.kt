package org.sopt.dosopttemplate
import androidx.annotation.DrawableRes

sealed class UserProfile {
    data class My(
        @DrawableRes val profileImage: Int,
        val name: String,
        val message: String
    ) : UserProfile()

    data class User(
        @DrawableRes val profileImage: Int,
        val name: String,
        val message: String
    ) : UserProfile()
}