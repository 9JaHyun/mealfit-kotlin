package com.mealfitkotiln.food.adapter.`in`.web

import com.mealfitkotiln.food.application.service.CommandFoodService
import com.mealfitkotiln.food.application.FoodDtoAssembler
import com.mealfitkotiln.food.application.service.QueryFoodService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/food")
@RestController
internal class FoodController(
    private val queryFoodService: QueryFoodService,
    private val commandFoodService: CommandFoodService,
) {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 6
    }

    @PostMapping("/v1")
    fun saveFood(@RequestBody request: FoodSaveRequest): ResponseEntity<String> {
        val requestDto = FoodDtoAssembler.foodSaveRequestDto(request)
        commandFoodService.saveFood(requestDto)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body("생성 완료")
    }

    @GetMapping("/v1")
    fun getFood(@RequestParam(name = "foodName") foodName: String,
    @RequestParam(defaultValue = "" + Long.MAX_VALUE) lastId: Long,
                @RequestParam(defaultValue = "id") sortKey: String,
                @RequestParam(defaultValue = "false") isAsc:Boolean,
                @RequestParam(defaultValue = DEFAULT_PAGE_SIZE.toString()) pageSize: Int): ResponseEntity<String> {
        val requestDto = FoodDtoAssembler.foodInfoRequestDto(foodName, pageSize, sortKey, isAsc, lastId)
        queryFoodService.getFoodsByName(requestDto)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body("생성 완료")
    }

}