package com.mealfitkotiln.food.application.service

import com.mealfitkotiln.food.application.port.`in`.FoodSaveRequestDto
import com.mealfitkotiln.food.application.port.`in`.CommandFoodUseCase
import com.mealfitkotiln.food.application.port.`in`.FoodUpdateRequestDto
import com.mealfitkotiln.food.application.port.out.CommandFoodPort
import com.mealfitkotiln.food.application.port.out.QueryFoodPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class CommandFoodService(
    private val queryFoodPort: QueryFoodPort,
    private val commandFoodPort: CommandFoodPort
) : CommandFoodUseCase{

    @Transactional
    override fun saveFood(foodSaveRequestDto: FoodSaveRequestDto) {
        val food = foodSaveRequestDto.toEntity()
        commandFoodPort.saveFood(food)
    }

    override fun updateFood(foodUpdateRequestDto: FoodUpdateRequestDto) {
        TODO("Not yet implemented")
    }

    fun deleteAll() {
        commandFoodPort.deleteAll()
    }
}