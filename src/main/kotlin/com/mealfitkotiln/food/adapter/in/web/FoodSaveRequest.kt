package com.mealfitkotiln.food.adapter.`in`.web

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class FoodSaveRequest(
    @NotBlank val foodName: String,
    @NotBlank @Size(min = 0) val oneServing: Double,
    @NotBlank @Size(min = 0) val kcal: Double,
    @NotBlank @Size(min = 0) val carbs: Double,
    @NotBlank @Size(min = 0) val protein: Double,
    @NotBlank @Size(min = 0) val fat: Double,
    @NotBlank val madeBy: String,
)