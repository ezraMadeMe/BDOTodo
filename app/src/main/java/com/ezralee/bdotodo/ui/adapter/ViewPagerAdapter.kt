package com.ezralee.bdotodo.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, private val titles: List<String>, private val fragments: List<Fragment>)
    : FragmentStatePagerAdapter(fragmentManager) {

    private val tabs: Int = titles.size

    private val PAGE1 = 0
    private val PAGE2 = 0
    private val PAGE3 = 0

    override fun getCount(): Int {
        return tabs
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            PAGE1 -> fragment = fragments[0]
            PAGE2 -> fragment = fragments[1]
            PAGE3 -> fragment = fragments[2]
            else -> error("error")
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}