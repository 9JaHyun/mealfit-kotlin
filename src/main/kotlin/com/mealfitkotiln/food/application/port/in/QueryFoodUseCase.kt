package com.mealfitkotiln.food.application.port.`in`

interface QueryFoodUseCase {

    fun getFoodsByName(foodInfoRequestDto: FoodInfoRequestDto): List<FoodInfoResponse>

    fun getFoodById(id: Long): FoodInfoResponse
}