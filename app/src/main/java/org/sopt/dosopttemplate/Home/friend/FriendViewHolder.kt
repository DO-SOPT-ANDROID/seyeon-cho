package org.sopt.dosopttemplate.Home.friend

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.Home.friend.RealUserProfile

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(userRealUserProfileData: RealUserProfile.RealUser) {
        with(binding) {
            Glide.with(ivProfile)
                .load(userRealUserProfileData.profileImage)
                .into(ivProfile)
            tvName.text = userRealUserProfileData.name
            tvSelfMessage.text = userRealUserProfileData.message
        }
    }
    }
