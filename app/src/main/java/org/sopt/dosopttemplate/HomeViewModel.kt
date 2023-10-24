package org.sopt.dosopttemplate

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val mockUserProfileLists = mutableListOf<UserProfile>(
        UserProfile.My (
        profileImage = R.drawable.myimage,
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
            message = "하이티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "이삭",
            message = "티비티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "박동민",
            message = "일본티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "컴네",
            message = "시험티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "컴특",
            message = "드래곤티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "데베설",
            message = "심 티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "디논",
            message = "주균 티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "숙대",
            message = "눈송티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "소웨",
            message = "컴과티비"
        ),
        UserProfile.User(
            profileImage = R.drawable.myimage,
            name = "솝트",
            message = "안드티비"
        )
    )
}