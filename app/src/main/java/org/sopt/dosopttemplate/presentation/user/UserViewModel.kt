package org.sopt.dosopttemplate.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.repository.UserRepository
import org.sopt.dosopttemplate.presentation.login.UiState
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {
    private val _userState = MutableStateFlow<UiState<List<UserEntity>>>(UiState.Loading)
    val userState: StateFlow<UiState<List<UserEntity>>> get() = _userState

    fun getUserFromServer(page: Int) {
        _userState.value = UiState.Loading
        viewModelScope.launch {
            repository.getUserList(page)
                .onSuccess { userList ->
                    val User = userList.map { userEntity ->
                        UserEntity(
                            avatar = userEntity.avatar,
                            email = userEntity.email,
                            firstName = userEntity.firstName,
                        )
                    }
                    _userState.value = UiState.Success(User)
                }
                .onFailure { exception ->
                    _userState.value = UiState.Error
                }
        }
    }
}
