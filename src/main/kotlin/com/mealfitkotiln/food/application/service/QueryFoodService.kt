package com.mealfitkotiln.food.application.service

import com.mealfitkotiln.food.application.port.`in`.FoodInfoRequestDto
import com.mealfitkotiln.food.application.port.`in`.FoodInfoResponse
import com.mealfitkotiln.food.application.port.`in`.QueryFoodUseCase
import com.mealfitkotiln.food.application.port.out.QueryFoodPort
import org.springframework.stereotype.Service

@Service
internal class QueryFoodService(
    private val queryFoodPort: QueryFoodPort
) : QueryFoodUseCase {

    override fun getFoodsByName(foodInfoRequestDto: FoodInfoRequestDto): List<FoodInfoResponse> {
        return queryFoodPort.getFoodsByName(
            foodInfoRequestDto.foodName,
            foodInfoRequestDto.size,
            foodInfoRequestDto.sortKey,
            foodInfoRequestDto.isAsc,
            foodInfoRequestDto.lastId
        ).map { food -> FoodInfoResponse(food) }
    }

    override fun getFoodById(id: Long): FoodInfoResponse {
        return FoodInfoResponse(queryFoodPort.getFoodById(id))
    }
}