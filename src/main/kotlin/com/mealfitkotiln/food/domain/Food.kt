package com.mealfitkotiln.food.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Food(

    var foodName: String,       // 음식명

    var oneServing: Double = 0.0,  // 1회제공량

    var kcal: Double = 0.0,     // 칼로리

    var carbs: Double = 0.0,    // 탄수화물

    var protein: Double = 0.0,  // 단백질

    var fat: Double = 0.0,      // 지방

    var madeBy: String,         // 제조사

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Food

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}