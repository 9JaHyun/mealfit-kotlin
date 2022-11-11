package com.mealfitkotiln.food.application.port.`in`

class FoodInfoRequestDto(
    val foodName: String,
    val size: Int,
    val sortKey: String = "id",
    val isAsc: Boolean = false,
    val lastId: Long,
) {

}
