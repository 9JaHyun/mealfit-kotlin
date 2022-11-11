package com.mealfitkotiln.food.application.port.`in`

interface CommandFoodUseCase {

    fun saveFood(foodSaveRequestDto: FoodSaveRequestDto)

    fun updateFood(foodUpdateRequestDto: FoodUpdateRequestDto)
}