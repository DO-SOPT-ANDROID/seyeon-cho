package org.sopt.dosopttemplate.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(RealUserData: RealUserProfile.RealUser) {
        with(binding) {
            Glide.with(ivProfile)
                .load(RealUserData.profileImage)
                .into(ivProfile)
            tvName.text = RealUserData.name
            tvSelfMessage.text = RealUserData.message
        }
    }
    }

