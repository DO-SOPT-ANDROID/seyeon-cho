package org.sopt.dosopttemplate.user

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.sopt.dosopttemplate.data.api.ServicePool
import org.sopt.dosopttemplate.data.user.ResponseUserDto
import org.sopt.dosopttemplate.databinding.FragmentUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding ?: throw IllegalStateException("바인딩 초기화 안됐어유")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UserAdapter()
        binding.rvUser.adapter = adapter
        getRecyclerView()
    }

    private fun getRecyclerView() {
        ServicePool.userService.getUserList().enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserDto? = response.body()

                    data?.let {
                        val adapter = binding.rvUser.adapter as? UserAdapter
                        adapter?.setUserList(it.data)
                    }
                } else {
                    Toast.makeText(requireContext(), "서버 에러 발생", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                Toast.makeText(requireContext(), "네트워크 연결 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
