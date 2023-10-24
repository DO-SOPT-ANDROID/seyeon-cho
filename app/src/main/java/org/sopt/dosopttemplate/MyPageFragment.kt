package org.sopt.dosopttemplate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding


class MyPageFragment : Fragment() {
    private var _binding : FragmentMyPageBinding ? = null
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding){"바인딩 에러"}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getString("user_id")
        val major = arguments?.getString("user_major")
        val name = arguments?.getString("user_name")

        with(binding) {
            myId.text = userId
            myMajor.text = major
            myName.text = name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


