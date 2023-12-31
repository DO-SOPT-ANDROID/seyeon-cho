package org.sopt.dosopttemplate

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val mockUserProfileLists = mutableListOf<UserProfile>(
        UserProfile.My (
        profileImage = R.drawable.boong1,
        name = "조세연",
        message = "붕어의 계절티비",
    ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "경지현",
            message = "비티비타오백"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "박강희",
            message = "오늘 생일티비!"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "이삭",
            message = "안드짱티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "박동민",
            message = "일본티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "붕어빵",
            message = "슈크림근본"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "붕어",
            message = "예?"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "붕",
            message = "붕붕아"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "시험",
            message = "멈춰티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "숙대",
            message = "눈송티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "새벽",
            message = "배고픔티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "솝트",
            message = "안드티비"
        )
    )
}