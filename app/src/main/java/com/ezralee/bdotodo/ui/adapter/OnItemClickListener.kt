package com.ezralee.bdotodo.ui.adapter

import com.ezralee.bdotodo.data.model.HistoryData

interface OnItemClickListener{
    fun onItemClick(data: HistoryData)
}