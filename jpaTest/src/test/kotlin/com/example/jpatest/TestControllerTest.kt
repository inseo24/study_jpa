package com.example.jpatest

import com.example.jpatest.validation.TestController
import com.example.jpatest.validation.TestDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.validation.Validator

@SpringBootTest
class TestControllerTest{

    @Autowired
    lateinit var testController: TestController

    @Autowired
    lateinit var validatorInjected: Validator

    @Test
    fun withoutValidAnnotation() {
        val testDto = TestDto(name = "", age = 3)
        val response = testController.test(testDto)

        println(response)
    }

}