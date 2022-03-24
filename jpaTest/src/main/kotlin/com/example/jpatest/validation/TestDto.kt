package com.example.jpatest.validation

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class TestDto(
    @NotBlank(message = "이름은 필수입니다")
    val name: String,

    @Min(value = 20, message = "20살 이후부터 가입 가능합니다")
    val age: Int,
) {
}