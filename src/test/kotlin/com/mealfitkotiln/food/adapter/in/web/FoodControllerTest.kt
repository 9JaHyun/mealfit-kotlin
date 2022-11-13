package com.mealfitkotiln.food.adapter.`in`.web

import com.mealfitkotiln.food.application.port.`in`.CommandFoodUseCase
import com.mealfitkotiln.food.application.port.`in`.FoodInfoResponse
import com.mealfitkotiln.food.application.port.`in`.QueryFoodUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.requestParameters
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@ExtendWith(RestDocumentationExtension::class)
@WebMvcTest(FoodController::class)
class FoodControllerTest @Autowired constructor(
    @MockBean private val queryFoodUseCase: QueryFoodUseCase,
    @MockBean private val commandFoodUseCase: CommandFoodUseCase,
) {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setUp(
        webApplicationContext: WebApplicationContext,
        restDocumentationContextProvider: RestDocumentationContextProvider
    ) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .apply<DefaultMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(
                    restDocumentationContextProvider
                ).operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint())
            )
            .build()
    }

    @Test
    fun getFoodsByName() {
        given(queryFoodUseCase.getFoodsByName(any())).willReturn(
            mutableListOf(FoodInfoResponse(1, "사과", 100.0, 80.0, 15.0, 1.0, 0.0, "전국"))
        )

        mockMvc.perform(
            get("/api/food/v1")
                .characterEncoding("utf-8")
                .queryParam("foodName", "사과")
                .queryParam("sortKey", "id")
                .queryParam("isAsc", "false")
                .queryParam("pageSize", "6")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andDo(
                document(
                    "food/getFoodsByName",
                    requestParameters(
                        parameterWithName("foodName").description("음식명"),
                        parameterWithName("sortKey").description("정렬기준"),
                        parameterWithName("isAsc").description("오름차순 내림차순"),
                        parameterWithName("pageSize").description("한번에 노출할 개수")
                    ),
                    responseFields(
                        fieldWithPath("[].foodId").type(Double::class).description("음식 ID"),
                        fieldWithPath("[].foodName").type(String::class).description("음식 이름"),
                        fieldWithPath("[].oneServing").type(Double::class).description("1회 제공량"),
                        fieldWithPath("[].kcal").type(Double::class).description("칼로리"),
                        fieldWithPath("[].carbs").type(Double::class).description("탄수화물"),
                        fieldWithPath("[].protein").type(Double::class).description("단백질"),
                        fieldWithPath("[].fat").type(Double::class).description("지방"),
                        fieldWithPath("[].madeBy").type(String::class).description("제조사")
                    )
                )
            )
    }
}