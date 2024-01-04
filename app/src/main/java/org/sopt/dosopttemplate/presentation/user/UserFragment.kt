package org.sopt.dosopttemplate.presentation.user

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.model.response.ResponseUserDto
import org.sopt.dosopttemplate.databinding.FragmentUserBinding
import org.sopt.dosopttemplate.di.ServicePool
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.presentation.login.UiState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding ?: throw IllegalStateException("바인딩 초기화 안됐어유")
    private val UserViewModel by viewModels<UserViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectAdapter()
        observeUserState()
        UserViewModel.getUserFromServer(2)
    }

    private lateinit var userAdapter: UserAdapter
    private fun connectAdapter() {
        userAdapter = UserAdapter()
        binding.rvUser.adapter = userAdapter
    }

    private fun observeUserState() {
        UserViewModel.userState.flowWithLifecycle(lifecycle).onEach { userState ->
            when (userState) {
                is UiState.Success -> {
                    getUser(userState.data)
                }
                is UiState.Error -> {
                    Toast.makeText(requireContext(), "서버 에러", Toast.LENGTH_SHORT).show()
                }
                is UiState.Loading -> {
                    Toast.makeText(requireContext(), "로딩중인데염", Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun getUser(userList: List<UserEntity>) {
        userAdapter.setUserList(userList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}