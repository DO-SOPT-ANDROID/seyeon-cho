package org.sopt.dosopttemplate.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.Home.friend.FriendViewHolder
import org.sopt.dosopttemplate.Home.myprofile.MyProfileViewHolder
import org.sopt.dosopttemplate.Home.friend.RealUserProfile
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMyprofileBinding

class MainAdapter(requireContext: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var profileList: MutableList<RealUserProfile>

    private val View_Myprofile = 0
    private val View_Friend = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            View_Myprofile -> {
                val binding = ItemMyprofileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MyProfileViewHolder(binding)
            }
            View_Friend -> {
                val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FriendViewHolder(binding)
            }
            else -> throw IllegalArgumentException("유효하지 않소")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = profileList[position]
        when (holder) {
            is MyProfileViewHolder -> {
                holder.onBind(item as RealUserProfile.My )
            }
            is FriendViewHolder -> {
                holder.onBind(item as RealUserProfile.RealUser)
            }
        }
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun getItemViewType(position: Int): Int = when(profileList[position]){
        is RealUserProfile.My -> View_Myprofile
        is RealUserProfile.RealUser -> View_Friend
    }
}


