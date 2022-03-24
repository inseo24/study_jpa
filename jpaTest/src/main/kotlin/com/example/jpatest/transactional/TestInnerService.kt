package com.example.jpatest.transactional

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TestInnerService(
    val testMoreInnerService: TestMoreInnerService,
    val testOuterRepository: TestOuterRepository
) {

    fun throwRuntimeException() {
        testOuterRepository.save(TestOuterEntity(id = 2, content = "content 2"))
        throwRuntimeExceptionInInner()
    }

    fun throwCheckedException() {
        testOuterRepository.save(TestOuterEntity(id = 2, content = "content 2"))
        throwCheckedExceptionInInner()
    }

    fun tryCatchRuntimeException() {
        testOuterRepository.save(TestOuterEntity(id = 2, content = "content 2"))
        try {
            throwRuntimeExceptionInInner()
        } catch (e: RuntimeException) {
            println(e.message)
        }
    }

    fun tryCatchCheckedException() {
        testOuterRepository.save(TestOuterEntity(id = 2, content = "content 2"))
        try {
            throwCheckedExceptionInInner()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun moreInnerServiceRuntimeException() {
        testOuterRepository.save(TestOuterEntity(id = 2, content = "content 2"))
        testMoreInnerService.throwRuntimeException()
    }

    fun moreInnerServiceCheckedException() {
        testOuterRepository.save(TestOuterEntity(id = 2, content = "content 2"))
        testMoreInnerService.throwCheckedException()
    }

    private fun throwRuntimeExceptionInInner() {
        throw RuntimeException("this is runtime exception")
    }

    private fun throwCheckedExceptionInInner() {
        throw Exception("this is just exception")
    }


}