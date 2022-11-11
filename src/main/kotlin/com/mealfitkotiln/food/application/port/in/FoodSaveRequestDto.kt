package com.mealfitkotiln.food.application.port.`in`

import com.mealfitkotiln.food.domain.Food

class FoodSaveRequestDto(
    private val foodName: String,
    private val oneServing: Double,
    private val kcal: Double,
    private val carbs: Double,
    private val protein: Double,
    private val fat: Double,
    private val madeBy: String,
) {

    fun toEntity(): Food =
        Food(foodName, oneServing, kcal, carbs, protein, fat, madeBy)
}
