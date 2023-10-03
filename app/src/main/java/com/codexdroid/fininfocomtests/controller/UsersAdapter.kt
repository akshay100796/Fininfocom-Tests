package com.codexdroid.fininfocomtests.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.fininfocomtests.R
import com.codexdroid.fininfocomtests.databinding.LayoutRecyclerUsersBinding
import com.codexdroid.fininfocomtests.models.Users
import com.codexdroid.fininfocomtests.utils.AppConstants

class UsersAdapter(
    private val context: Context,
    private val users: List<Users>, private val sortBy: String): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private val background = ResourcesCompat.getDrawable(context.resources,R.drawable.drf_black_back,null)
    private val colorWhite = ResourcesCompat.getColor(context.resources,R.color.white,null)
    private val colorBlue = ResourcesCompat.getColor(context.resources,R.color.blue,null)

    inner class UserViewHolder(val binding: LayoutRecyclerUsersBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutRecyclerUsersBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {

            val user = users[position]
            idName.text = user.name
            idAge.text = context.getString(R.string.year_old, user.age)
            idCity.text = user.city

            when (sortBy) {
                AppConstants.BY_AGE -> {
                    idCard.strokeColor = colorWhite
                    idAge.background = background
                }
                AppConstants.BY_CITY -> {
                    idCard.strokeColor = colorWhite
                    idCity.background = background
                }
                AppConstants.BY_NAME -> {
                    idCard.strokeColor = colorWhite
                    idName.background = background
                }
                else -> {
                    idCard.strokeColor = colorBlue
                }
            }

        }
    }

    override fun getItemCount(): Int = users.size
}