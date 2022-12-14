package com.ezralee.bdotodo.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.ezralee.bdotodo.databinding.BottomSheetHistoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HistoryBottomSheetDialog : BottomSheetDialogFragment() {
    val binding: BottomSheetHistoryBinding by lazy { BottomSheetHistoryBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //입력한 검색어 넘겨서 DB 쿼리
        binding.historyKeyword.text
        //선택한 카테고리에 속한 히스토리만 필터
        binding.historyCategory[1]
        //선택한 날짜로 리사이클러의 포커스 이동
        binding.historyDate.setOnClickListener {  }

    }
}