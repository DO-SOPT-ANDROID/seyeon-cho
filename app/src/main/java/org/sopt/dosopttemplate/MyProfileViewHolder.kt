package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMyprofileBinding

class MyProfileViewHolder(private val binding: ItemMyprofileBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(userUserProfileData: UserProfile.My) {
        binding.ivProfile.setImageResource(userUserProfileData.profileImage)
        binding.tvName.text= userUserProfileData.name
        binding.tvSelfMessage.text= userUserProfileData.message
    }

}
