package com.mealfitkotiln.food.application.port.`in`

import com.mealfitkotiln.food.domain.Food
import java.io.Serializable

class FoodInfoResponse(
    val foodId: Long?,
    val foodName: String,
    val oneServing: Double,
    val kcal: Double,
    val carbs: Double,
    val protein: Double,
    val fat: Double,
    val madeBy: String,
) : Serializable {

    constructor(food: Food) :
            this(
                food.id,
                food.foodName,
                food.oneServing,
                food.kcal,
                food.carbs,
                food.protein,
                food.fat, food.madeBy
            )
}
