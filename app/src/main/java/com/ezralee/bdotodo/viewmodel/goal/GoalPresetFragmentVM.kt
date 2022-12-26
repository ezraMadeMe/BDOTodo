package com.ezralee.bdotodo.viewmodel.goal

import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.model.GoalPreset
import com.ezralee.bdotodo.ui.adapter.BindAdapter

class GoalPresetFragmentVM(val contract: GoalPresetFragmentContract) {
    interface GoalPresetFragmentContract{

    }

    //리사이클러뷰의 특정 프리셋 클릭 시 해당 프리셋의 상세정보가 Set머시기로 로드되며 액티비티 이동

    /////////////////////////////////////////////////// 리사이클러뷰
    val recyclerAdapter = BindAdapter(
        id = R.layout.goal_preset_recycler_item,
        items = listOf<GoalPreset>(),
        listener = this
    )
}