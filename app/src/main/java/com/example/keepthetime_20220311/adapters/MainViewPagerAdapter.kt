package com.example.keepthetime_20220311.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.keepthetime_20220311.fragments.AppointmentListFragment
import com.example.keepthetime_20220311.fragments.MyProfileFragment

class MainViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {

        return when(position){

            0 -> AppointmentListFragment()
            else -> MyProfileFragment()

        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "약속 목록"
            else -> "내 프로필"
        }
    }

}