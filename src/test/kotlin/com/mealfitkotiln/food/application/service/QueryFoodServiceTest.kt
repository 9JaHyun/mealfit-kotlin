package com.mealfitkotiln.food.application.service

import com.mealfitkotiln.food.application.port.`in`.FoodInfoRequestDto
import com.mealfitkotiln.food.application.port.`in`.FoodSaveRequestDto
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class QueryFoodServiceTest @Autowired constructor(
    private val queryFoodService: QueryFoodService,
    private val commandFoodService: CommandFoodService
) {

    @AfterEach
    fun tearDown() {
        commandFoodService.deleteAll()
    }

    @Nested
    @DisplayName("findFoods() 메서드는")
    inner class Testing_findFoods {

        @DisplayName("FoodSaveRequestDto가 완전히 입력되면 성공")
        @Test
        fun findFoods_success() {
            // given
            commandFoodService.saveFood(
                FoodSaveRequestDto(
                    "음식1",
                    100.0,
                    150.0,
                    30.0,
                    10.0,
                    11.0,
                    "제조사1"
                )
            )
            commandFoodService.saveFood(
                FoodSaveRequestDto(
                    "음식2",
                    110.0,
                    200.0,
                    35.0,
                    15.0,
                    16.0,
                    "제조사2"
                )
            )
            commandFoodService.saveFood(
                FoodSaveRequestDto(
                    "음식3",
                    120.0,
                    300.0,
                    40.0,
                    20.0,
                    21.0,
                    "제조사3"
                )
            )

            // when
            val findResult = queryFoodService.getFoodsByName(
                FoodInfoRequestDto("음식", 10, "id", false, 10)
            )

            // then
            assertThat(findResult).hasSize(3)
            assertThat(findResult).extracting("foodName")
                .containsExactlyInAnyOrder("음식1", "음식2", "음식3")
            assertThat(findResult).extracting("oneServing")
                .containsExactlyInAnyOrder(100.0, 110.0, 120.0)
            assertThat(findResult).extracting("kcal").containsExactlyInAnyOrder(150.0, 200.0, 300.0)
            assertThat(findResult).extracting("carbs").containsExactlyInAnyOrder(30.0, 35.0, 40.0)
            assertThat(findResult).extracting("protein").containsExactlyInAnyOrder(10.0, 15.0, 20.0)
            assertThat(findResult).extracting("fat").containsExactlyInAnyOrder(11.0, 16.0, 21.0)
            assertThat(findResult).extracting("madeBy")
                .containsExactlyInAnyOrder("제조사1", "제조사2", "제조사3")
        }

        @DisplayName("page size 따라 개수가 달라진다.")
        @Test
        fun findFoods_lastId() {
            // given
            commandFoodService.saveFood(
                FoodSaveRequestDto(
                    "음식1",
                    100.0,
                    150.0,
                    30.0,
                    10.0,
                    11.0,
                    "제조사1"
                )
            )
            commandFoodService.saveFood(
                FoodSaveRequestDto(
                    "음식2",
                    110.0,
                    200.0,
                    35.0,
                    15.0,
                    16.0,
                    "제조사2"
                )
            )
            commandFoodService.saveFood(
                FoodSaveRequestDto(
                    "음식3",
                    120.0,
                    300.0,
                    40.0,
                    20.0,
                    21.0,
                    "제조사3"
                )
            )

            // when
            val findResult = queryFoodService.getFoodsByName(
                FoodInfoRequestDto("음식", 2, "id", false, 10)
            )

            // then
            assertThat(findResult).hasSize(2)
            assertThat(findResult).extracting("foodName")
                .containsExactlyInAnyOrder("음식3", "음식2")
            assertThat(findResult).extracting("oneServing")
                .containsExactlyInAnyOrder(120.0, 110.0)
            assertThat(findResult).extracting("kcal")
                .containsExactlyInAnyOrder(300.0, 200.0)
            assertThat(findResult).extracting("carbs")
                .containsExactlyInAnyOrder(40.0, 35.0)
            assertThat(findResult).extracting("protein")
                .containsExactlyInAnyOrder(20.0, 15.0)
            assertThat(findResult).extracting("fat")
                .containsExactlyInAnyOrder(21.0, 16.0)
            assertThat(findResult).extracting("madeBy")
                .containsExactlyInAnyOrder("제조사3", "제조사2")
        }
    }
}