package com.example.keepthetime_20220311.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keepthetime_20220311.R
import com.example.keepthetime_20220311.datas.UserData

class MyFriendRecyclerAdapter(
    val mContext: Context,
    val mList: List<UserData>
): RecyclerView.Adapter<MyFriendRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = view.findViewById<TextView>(R.id.txtNickname)
        val imgSosialLogo = view.findViewById<ImageView>(R.id.imgSosialLogo)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)

        fun bind(data: UserData){

                Glide.with(mContext).load(data.profile_img).into(imgProfile)
                txtNickname.text = data.nick_name
            when (data.provider) {
                "default" -> {
                    imgSosialLogo.visibility = View.GONE

                    txtEmail.text = data.email
                }
                "kakao" -> {
                    imgSosialLogo.visibility = View.VISIBLE
                    imgSosialLogo.setImageResource(R.drawable.kakao_logo)
                    txtEmail.text = "카카오 로그인"
                }
                "facebook" -> {
                    imgSosialLogo.visibility = View.VISIBLE
                    imgSosialLogo.setImageResource(R.drawable.facebook_logo)
                    txtEmail.text = "페이스북 로그인"
                }
                "naver" -> {
                    imgSosialLogo.visibility = View.VISIBLE
                    imgSosialLogo.setImageResource(R.drawable.naver_logo)
                    txtEmail.text = "네이버 로그인"
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.my_friend_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = mList[position]
        holder.bind(data)
    }

    override fun getItemCount()= mList.size
}