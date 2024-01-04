package org.sopt.dosopttemplate.presentation.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemUserBinding
import org.sopt.dosopttemplate.domain.entity.UserEntity

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private val userList = mutableListOf<UserEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setUserList(userData: List<UserEntity>) {
        userList.clear()
        userList.addAll(userData)
        notifyDataSetChanged()
    }
}
