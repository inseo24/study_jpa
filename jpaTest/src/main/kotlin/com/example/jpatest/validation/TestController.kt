package com.example.jpatest.validation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class TestController {

    @GetMapping("/")
    fun test(@RequestBody @Valid testDto: TestDto) : String {
        println("name = " + testDto.name);
        println("age = " + testDto.age);

        return "test complete"
    }
}