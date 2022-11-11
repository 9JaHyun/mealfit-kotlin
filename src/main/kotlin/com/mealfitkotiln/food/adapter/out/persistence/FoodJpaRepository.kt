package com.mealfitkotiln.food.adapter.out.persistence

import com.mealfitkotiln.food.domain.Food
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface FoodJpaRepository: JpaRepository<Food, Long> {
    fun findByIdLessThanAndFoodNameContaining(
        lastId: Long,
        foodName: String?,
        pageable: Pageable?
    ): List<Food>
}