package com.example.jpatest.transactional

import com.example.jpatest.extenstion.findByIdOrThrowCheckedException
import com.example.jpatest.extenstion.findByIdOrThrowRuntimeException
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

    fun kotlinRunWithCheckedExceptionTest() {
        println("inner service 시작")
        val entity = testOuterRepository.findByIdOrThrowCheckedException(5)
        entity.run {
            val afterRuntEntity = testOuterRepository.save(TestOuterEntity(id = 2, content = "content 2"))
            println("과연 실행될 것인가!!")
            println(afterRuntEntity.content)
        }
    }

    fun kotlinRunWithRuntimeExceptionTest() {
        println("inner service 시작")
        val entity = testOuterRepository.findByIdOrThrowRuntimeException(5)
        entity.run {
            val afterRuntEntity = testOuterRepository.save(TestOuterEntity(id = 2, content = "content 2"))
            println("과연 실행될 것인가!!")
            println(afterRuntEntity.content)
        }
    }

    private fun throwRuntimeExceptionInInner() {
        throw RuntimeException("this is runtime exception")
    }

    private fun throwCheckedExceptionInInner() {
        throw Exception("this is just exception")
    }


}