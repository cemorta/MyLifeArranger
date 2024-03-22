package com.example.mylifearranger.feature_planner.presentation.add_edit_plan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylifearranger.feature_planner.domain.model.Plan
import javax.inject.Inject

class SharedViewModel @Inject constructor() : ViewModel() {
    val sharedState: MutableLiveData<Plan?> = MutableLiveData()

    fun setSharedState(plan: Plan) {
        sharedState.value = plan
    }

    fun getSharedState(): Plan? {
        return sharedState.value
    }

    fun setEndDateTimestamp(endDateTimestamp: Long) {
        sharedState.value?.endDateTimestamp = endDateTimestamp
    }

    fun clearSharedState() {
        sharedState.value = null
    }
}
