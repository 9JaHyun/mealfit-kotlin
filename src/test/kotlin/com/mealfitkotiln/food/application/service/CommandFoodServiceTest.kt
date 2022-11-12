package com.mealfitkotiln.food.application.service

import com.mealfitkotiln.food.application.port.out.CommandFoodPort
import com.mealfitkotiln.food.application.port.out.QueryFoodPort
import com.mealfitkotiln.food.domain.Food
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CommandFoodServiceTest @Autowired constructor(
    private val commandFoodPort: CommandFoodPort,
    private val queryFoodPort: QueryFoodPort
) {

    @AfterEach
    fun tearDown() {
        commandFoodPort.deleteAll()
    }

    @Nested
    @DisplayName("saveFood() 메서드는")
    inner class Testing_saveFood {

        @DisplayName("FoodSaveRequestDto가 완전히 입력되면 성공")
        @Test
        fun saveFood_success() {
            // given
            val food = Food("사과", 100.0,
                80.0, 15.0, 1.0, 1.0, "전국")

            // when
            commandFoodPort.saveFood(food)

            // then
            val findResult = queryFoodPort.getFoodById(1L)
            assertThat(findResult.id).isEqualTo(1)
            assertThat(findResult.foodName).isEqualTo("사과")
            assertThat(findResult.oneServing).isEqualTo(100.0)
            assertThat(findResult.kcal).isEqualTo(80.0)
            assertThat(findResult.carbs).isEqualTo(15.0)
            assertThat(findResult.protein).isEqualTo(1.0)
            assertThat(findResult.fat).isEqualTo(1.0)
            assertThat(findResult.madeBy).isEqualTo("전국")
        }
    }


}