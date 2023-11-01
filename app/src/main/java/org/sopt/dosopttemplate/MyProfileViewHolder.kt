package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMyprofileBinding

class MyProfileViewHolder(private val binding: ItemMyprofileBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(userUserProfileData: UserProfile.My) {
        with(binding) {
            Glide.with(ivProfile)
                .load(userUserProfileData.profileImage)
                .into(ivProfile)
            tvName.text = userUserProfileData.name
            tvSelfMessage.text = userUserProfileData.message
        }
    }
}
