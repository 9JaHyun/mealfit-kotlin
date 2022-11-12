package com.mealfitkotiln.food.application.port.out

import com.mealfitkotiln.food.domain.Food

interface CommandFoodPort {

    fun saveFood(food: Food)

    fun updateFood(food: Food)

    /**
     * @author 9JaHyun
     * @desc 테스트 도중 원활한 DB 초기화를 위해 deleteAll() 메서드를 추가했습니다.
     *       클라이언트들에게 제공하는 메서드가 아니기 때문에 주의를 해서 사용해야 합니다.
     *       현재 임시방편으로 만들어 놓았기 때문에 추후 deprecated 될 수 있습니다.
     * @since 1.0
     */
    fun deleteAll()
}