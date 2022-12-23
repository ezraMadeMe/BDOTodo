package com.ezralee.bdotodo.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.data.model.AccureItem
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.ui.adapter.goal.SetGoalVPAdapter
import com.ezralee.bdotodo.ui.adapter.history.HistoryAdapter
import com.ezralee.bdotodo.ui.adapter.main.MainActivityVPAdapter
import com.ezralee.bdotodo.viewmodel.goal.SetGoalActivityVM
import com.ezralee.bdotodo.viewmodel.main.MainActivityVM
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

object BindingAdapter {

    //////////////////////////////////////////main
    //사용자 이벤트
    // mainVP 어댑터 연결
    @BindingAdapter("setFsm", "setVM")
    @JvmStatic
    fun setMainVP(
        viewPager: ViewPager,
        fragmentManager: FragmentManager?,
        mainVm: MainActivityVM?,
        items: List<Fragment>
    ) {
        if (!items.isNullOrEmpty())
            viewPager.adapter?.run {
                if (this is MainActivityVPAdapter) {
                    //do something.
                }
            } ?: kotlin.run {
                if (fragmentManager != null)
                    viewPager.adapter = SetGoalVPAdapter(fragmentManager,items)
                viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {
                        //Nothing.
                    }

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        //Nothing.
                    }
                    override fun onPageSelected(position: Int) {
                        mainVm?.selectPosition(position)
                    }
                })
            }
    }

    //VP와 BNV 포지션 연동
    @BindingAdapter("sharePosition")
    @JvmStatic
    fun sharePosition(view: View, position: Int?) {
        if (position != null)
            when (view) {
                is ViewPager -> {
                    view.currentItem = position
                }
                is BottomNavigationView -> {
                    view.selectedItemId = position
                }
            }
    }

    /////////////////////////////////////////////

    // setGoalVP 어댑터 연결
    @BindingAdapter("setFsm", "setVM")
    @JvmStatic
    fun setGoalVP(
        viewPager: ViewPager,
        fragmentManager: FragmentManager?,
        mainVm: SetGoalActivityVM?,
        items: List<Fragment>
    ) {
        if (!items.isNullOrEmpty())
            viewPager.adapter?.run {
                if (this is SetGoalVPAdapter) {
                    //do something.
                }
            } ?: kotlin.run {
                if (fragmentManager != null)
                    viewPager.adapter = SetGoalVPAdapter(fragmentManager,items)
                viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {
                        //Nothing.
                    }

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        //Nothing.
                    }
                    override fun onPageSelected(position: Int) {
                        mainVm?.selectPosition(position)
                    }
                })
            }
    }


    //////////////////////////////////////////daily

    // daily 메인 어댑터 아이템 연결, 갱신
    @BindingAdapter("bindDaily", "setVM")
    @JvmStatic
    fun setDailyAdapter(recyclerView: RecyclerView, items: List<AccureItem>) {
        val myAdapter = recyclerView.adapter as DailyAdapter
        myAdapter.submitList(items.toMutableList())
        myAdapter.notifyDataSetChanged()
    }

    // daily 할일 추가 페이지 어댑터 아이템 연결, 갱신
    @BindingAdapter("bindTodo")
    @JvmStatic
    fun setTodoAdapter(recyclerView: RecyclerView, items: List<AccureItem>) {
        val myAdapter = recyclerView.adapter as TodoAdapter
        myAdapter.submitList(items.toMutableList())
        myAdapter.notifyDataSetChanged()
    }


    //////////////////////////////////////////history


    // history 어댑터 아이템 연결, 갱신
    @BindingAdapter("bindHistory")
    @JvmStatic
    fun setHistoryAdapter(recyclerView: RecyclerView, items: List<HistoryData>) {
        val myAdapter = recyclerView.adapter as HistoryAdapter
        myAdapter.submitList(items.toMutableList())
        myAdapter.notifyDataSetChanged()
    }

    // history 2차 어댑터 아이템 연결, 갱신
    @BindingAdapter("bindInnerHistory")
    @JvmStatic
    fun setInnerHistoryAdapter(recyclerView: RecyclerView, items: List<HistoryData>) {
        val myAdapter = recyclerView.adapter as HistoryInnerAdapter
        myAdapter.submitList(items.toMutableList())
        myAdapter.notifyDataSetChanged()
    }


    //////////////////////////////////////////goal

    // goal 메인 어댑터 아이템 연결, 갱신
    @BindingAdapter("bindGoal")
    @JvmStatic
    fun setGoalAdapter(recyclerView: RecyclerView, items: List<AccureItem>) {
        val myAdapter = recyclerView.adapter as GoalAdapter
        myAdapter.submitList(items.toMutableList())
        myAdapter.notifyDataSetChanged()
    }

    // goal 2차 어댑터 아이템 연결, 갱신
    @BindingAdapter("bindInnerGoal")
    @JvmStatic
    fun setGoalInnerAdapter(recyclerView: RecyclerView, items: List<AccureItem>) {
        val myAdapter = recyclerView.adapter as GoalInnerAdapter
        myAdapter.submitList(items.toMutableList())
        myAdapter.notifyDataSetChanged()
    }

    // goal 3차 어댑터 아이템 연결, 갱신
    @BindingAdapter("bindInnerInnerGoal")
    @JvmStatic
    fun setGoalInnerInnerAdapter(recyclerView: RecyclerView, items: List<AccureItem>) {
        val myAdapter = recyclerView.adapter as GoalInnerInnerAdapter
        myAdapter.submitList(items.toMutableList())
        myAdapter.notifyDataSetChanged()
    }

    // goal 설정 시 task 어댑터 아이템 연결, 갱신
    @BindingAdapter("bindTask")
    @JvmStatic
    fun setTaskAdapter(recyclerView: RecyclerView, items: List<AccureItem>) {
        val myAdapter = recyclerView.adapter as TaskAdapter
        myAdapter.submitList(items.toMutableList())
        myAdapter.notifyDataSetChanged()
    }

    // 이미지 바인딩
    @BindingAdapter("bindImage")
    @JvmStatic
    fun ImageView.setImage(imageUrl: Any) {
        Glide.with(this.context)
            .load(imageUrl)
            .into(this)
    }
}