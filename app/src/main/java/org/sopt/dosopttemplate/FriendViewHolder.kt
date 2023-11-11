package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(userUserProfileData: UserProfile.User) {
        with(binding) {
            Glide.with(ivProfile)
                .load(userUserProfileData.profileImage)
                .into(ivProfile)
            tvName.text = userUserProfileData.name
            tvSelfMessage.text = userUserProfileData.message
        }
    }
    }
