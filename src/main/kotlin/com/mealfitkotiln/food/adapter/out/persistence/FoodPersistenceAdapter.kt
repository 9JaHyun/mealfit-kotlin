package com.mealfitkotiln.food.adapter.out.persistence

import com.mealfitkotiln.findByIdOrThrow
import com.mealfitkotiln.food.application.port.out.CommandFoodPort
import com.mealfitkotiln.food.application.port.out.QueryFoodPort
import com.mealfitkotiln.food.domain.Food
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
internal class FoodPersistenceAdapter(
    private val foodJpaRepository: FoodJpaRepository
) : CommandFoodPort,
    QueryFoodPort {

    override fun saveFood(food: Food) {
        foodJpaRepository.save(food)
    }

    override fun updateFood(food: Food) {
        foodJpaRepository.save(food)
    }

    override fun getFoodsByName(
        foodName: String,
        sortKey: String,
        asc: Boolean,
        size: Int,
        lastId: Long
    ): List<Food> {
        val sort = if (asc) Sort.Direction.ASC else Sort.Direction.DESC

        return foodJpaRepository.findByIdLessThanAndFoodNameContaining(lastId, foodName, PageRequest.of(0, size, sort, sortKey))
    }


    override fun getFoodById(id: Long): Food {
        return foodJpaRepository.findByIdOrThrow(id)
    }
}