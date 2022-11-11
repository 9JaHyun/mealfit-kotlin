package com.mealfitkotiln.food.application.port.out

import com.mealfitkotiln.food.domain.Food

interface CommandFoodPort {

    fun saveFood(food: Food)

    fun updateFood(food: Food)
}