package com.mealfitkotiln.food.adapter.`in`.web

import com.mealfitkotiln.food.application.FoodDtoAssembler
import com.mealfitkotiln.food.application.port.`in`.CommandFoodUseCase
import com.mealfitkotiln.food.application.port.`in`.QueryFoodUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/food")
@RestController
internal class FoodController(
    private val queryFoodUseCase: QueryFoodUseCase,
    private val commandFoodUseCase: CommandFoodUseCase,
) {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 6
    }

    @PostMapping("/v1")
    fun saveFood(@RequestBody request: FoodSaveRequest): ResponseEntity<String> {
        val requestDto = FoodDtoAssembler.foodSaveRequestDto(request)
        commandFoodUseCase.saveFood(requestDto)

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
        queryFoodUseCase.getFoodsByName(requestDto)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body("생성 완료")
    }

}