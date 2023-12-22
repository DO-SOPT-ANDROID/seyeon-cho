package org.sopt.dosopttemplate.Home.friend
import androidx.annotation.DrawableRes

sealed class RealUserProfile {
    data class My(
        @DrawableRes val profileImage: Int,
        val name: String,
        val message: String
    ) : RealUserProfile()

    data class RealUser(
        @DrawableRes val profileImage: Int,
        val name: String,
        val message: String
    ) : RealUserProfile()
}