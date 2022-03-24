package com.example.jpatest.transactional

import org.springframework.stereotype.Service

@Service
class TestMoreInnerService(
    val testInnerRepository: TestInnerRepository
) {

    fun throwCheckedException() {
        testInnerRepository.save(TestInnerEntity(1, "inner content"))
        throwOuterCheckedException()
    }

    fun throwRuntimeException() {
        testInnerRepository.save(TestInnerEntity(1, "inner content"))
        throwOuterRuntimeException()
    }

    private fun throwOuterCheckedException() {
        throw Exception("this is checked exception")
    }

    private fun throwOuterRuntimeException() {
        throw RuntimeException("this is runtime exception")
    }

}