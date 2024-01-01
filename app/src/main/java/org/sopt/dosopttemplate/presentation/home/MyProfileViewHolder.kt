package org.sopt.dosopttemplate.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.dosopttemplate.databinding.ItemMyprofileBinding

class MyProfileViewHolder(private val binding: ItemMyprofileBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(userRealUserProfileData: RealUserProfile.My) {
        with(binding) {
            Glide.with(ivProfile)
                .load(userRealUserProfileData.profileImage)
                .into(ivProfile)
            tvName.text = userRealUserProfileData.name
            tvSelfMessage.text = userRealUserProfileData.message
        }
    }
}
