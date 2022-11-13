package com.mealfitkotiln.user.application.service

import com.mealfitkotiln.user.application.port.`in`.SignUpRequestDto
import com.mealfitkotiln.user.application.port.out.CommandUserPort
import com.mealfitkotiln.user.application.port.out.QueryUserPort
import com.mealfitkotiln.user.domain.ProviderType
import com.mealfitkotiln.user.domain.User
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class QueryUserServiceTest @Autowired constructor(
    private val queryUserService: QueryUserService,
    private val commandUserService: CommandUserService
) {

    @Nested
    @DisplayName("findUserById() 테스트는")
    inner class Testing_findUserById() {

        @DisplayName("해당하는 id가 존재하면 성공한다.")
        @Test
        fun findUserById_success() {

            // given
            commandUserService.signup(SignUpRequestDto("username1", "nickname1", "profileImage1", ProviderType.GOOGLE))
            commandUserService.signup(SignUpRequestDto("username2", "nickname2", "profileImage2", ProviderType.NAVER))
            commandUserService.signup(SignUpRequestDto("username3", "nickname3", "profileImage3", ProviderType.KAKAO))
            commandUserService.signup(SignUpRequestDto("username4", "nickname4", "profileImage4", ProviderType.GOOGLE))

            // when
            val user1 = queryUserService.findUserById(1)

            // then
        }
    }
}