package com.example.jpatest.transactional

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestOuterServiceTest{
    @Autowired
    lateinit var testOuterService: TestOuterService

    @Test
    fun localServiceThrowRuntimeException() {
        testOuterService.localThrowRuntimeException()
    }

    @Test
    fun innerServiceThrowCheckedException() {
        testOuterService.innerServiceThrowCheckedException()
    }

    @Test
    fun localServiceThrowCheckedException() {
        testOuterService.localThrowCheckedException()
    }

    @Test
    fun innerServiceThrowRuntimeException() {
        testOuterService.innerServiceThrowRuntimeException()
    }

    @Test
    fun tryCatchLocalThrowRuntimeException() {
        testOuterService.tryCatchLocalThrowRuntimeException()
    }

    @Test
    fun tryCatchLocalThrowCheckedException() {
        testOuterService.tryCatchLocalThrowCheckedException()
    }

    @Test
    fun tryCatchInnerThrowRuntimeException() {
        testOuterService.tryCatchInnerThrowRuntimeException()
    }

    @Test
    fun tryCatchInnerThrowCheckedException() {
        testOuterService.tryCatchInnerThrowCheckedException()
    }

    @Test
    fun tryCatchTwiceInnerThrowRuntimeException() {
        testOuterService.tryCatchTwiceInnerThrowRuntimeException()
    }

    @Test
    fun tryCatchTwiceInnerThrowCheckedException() {
        testOuterService.tryCatchTwiceInnerThrowCheckedException()
    }

    @Test
    fun moreInnerChecked() {
        testOuterService.moreInnerChecked()
    }

    @Test
    fun moreInnerRuntime() {
        testOuterService.moreInnerRuntime()
    }
}