package org.sopt.dosopttemplate.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.user.ResponseUserData
import org.sopt.dosopttemplate.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private val userList = mutableListOf<ResponseUserData>()

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

    fun setUserList(userData: List<ResponseUserData>) {
        userList.clear()
        userList.addAll(userData)
        notifyDataSetChanged()
    }
}
