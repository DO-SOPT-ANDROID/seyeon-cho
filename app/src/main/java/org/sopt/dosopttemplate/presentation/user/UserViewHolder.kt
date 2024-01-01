package org.sopt.dosopttemplate.presentation.user

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.dosopttemplate.databinding.ItemUserBinding
import org.sopt.dosopttemplate.domain.entity.UserEntity


class UserViewHolder(
    private val binding: ItemUserBinding
): RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: UserEntity) {
        binding.tvUserName.text = item.first_name
        binding.tvUserEmail.text = item.email
        Glide.with(binding.root.context)
            .load(item.avatar)
            .into(binding.ivUserAvatar)
    }

}