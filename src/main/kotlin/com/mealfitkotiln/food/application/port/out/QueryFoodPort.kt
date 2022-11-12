package com.mealfitkotiln.food.application.port.out

import com.mealfitkotiln.food.domain.Food

interface QueryFoodPort {

    fun getFoodsByName(
        foodName: String,
        size: Int,
        sortKey: String,
        asc: Boolean,
        lastId: Long
    ): List<Food>

    fun getFoodById(id: Long): Food
}