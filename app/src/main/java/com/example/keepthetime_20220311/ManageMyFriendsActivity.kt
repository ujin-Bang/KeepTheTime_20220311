package com.example.keepthetime_20220311

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepthetime_20220311.adapters.MyFriendRecyclerAdapterr
import com.example.keepthetime_20220311.databinding.ActivityManageMyFriendsBinding
import com.example.keepthetime_20220311.datas.BasicResponse
import com.example.keepthetime_20220311.datas.UserData
import com.example.keepthetime_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageMyFriendsActivity : BaseActivity() {

    lateinit var binding: ActivityManageMyFriendsBinding

    val mFriendList = ArrayList<UserData>()

    lateinit var mAdapter: MyFriendRecyclerAdapterr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_manage_my_friends)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnAddFriend.setOnClickListener {

            val myIntent = Intent(mContext, SearchUserActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {


        getMyFriendListFromServer()

        mAdapter = MyFriendRecyclerAdapterr(mContext, mFriendList)
        binding.myFriendsRecyclerView.adapter = mAdapter

        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    fun getMyFriendListFromServer() {

        apiList.getRequestFriendLis(
            ContextUtil.getLoginUserToken(mContext),
            "my" // 수락 완료된 친구목록만 불러오기
        ).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                mFriendList.clear()

                if(response.isSuccessful){

                    val br = response.body()!!

//                    br.data.friends는 UserData 목록으로 이미 내려옴
//                    목록의 내용물을 통째로 => mFriendList 변수의 내용물로 담자.

                    mFriendList.addAll(br.data.friends)

//                    어댑터 새로고침

                    mAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }
}