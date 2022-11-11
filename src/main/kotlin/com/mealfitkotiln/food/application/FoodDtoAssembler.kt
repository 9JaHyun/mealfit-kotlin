package com.mealfitkotiln.food.application

import com.mealfitkotiln.food.application.port.`in`.FoodSaveRequestDto
import com.mealfitkotiln.food.adapter.`in`.web.FoodSaveRequest
import com.mealfitkotiln.food.application.port.`in`.FoodInfoRequestDto

class FoodDtoAssembler {

    companion object {
        fun foodSaveRequestDto(saveRequest: FoodSaveRequest): FoodSaveRequestDto =
            FoodSaveRequestDto(
                saveRequest.foodName,
                saveRequest.oneServing,
                saveRequest.kcal,
                saveRequest.carbs,
                saveRequest.protein,
                saveRequest.fat,
                saveRequest.madeBy
            )

        fun foodInfoRequestDto(name: String, pageSize: Int, sortKey: String, isAsc: Boolean, lastId: Long): FoodInfoRequestDto =
            FoodInfoRequestDto(name, pageSize, sortKey, isAsc, lastId)
    }
}
