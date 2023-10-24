package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(userUserProfileData: UserProfile.User) {
        binding.ivProfile.setImageResource(userUserProfileData.profileImage)
        binding.tvName.text= userUserProfileData.name
        binding.tvSelfMessage.text= userUserProfileData.message
    }
}
