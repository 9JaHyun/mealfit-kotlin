package com.mealfitkotiln.food.application.port.out

import com.mealfitkotiln.food.domain.Food

interface QueryFoodPort {

    fun getFoodsByName(
        foodName: String,
        sortKey: String,
        asc: Boolean,
        size: Int,
        lastId: Long
    ): List<Food>

    fun getFoodById(id: Long): Food
}