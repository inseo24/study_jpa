package com.example.jpatest.transactional

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TestOuterService(
    val testInnerService: TestInnerService,
    val testOuterRepository: TestOuterRepository
) {

    // TODO 1-1. inner service 에서 Runtime Exception 발생 -> rollback : 됨
    // TODO Inner service 에 @Transactional 붙어 있는지 여부 차이 : 없음!(둘 다 rollback 됨)
    fun innerServiceThrowRuntimeException() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        testInnerService.throwRuntimeException()
    }

    // TODO 3. inner service 에서 Checked Exception 발생 -> rollback : 안됨
    // TODO Inner service 에 @Transactional 붙어 있는지 여부 차이 : 없음!(둘 다 rollback 안됨)
    fun innerServiceThrowCheckedException() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        testInnerService.throwCheckedException()
    }

    // TODO 2-1. 로컬에서 Runtime Exception 발생 -> rollback : 됨
    fun localThrowRuntimeException() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        throwLocalRuntimeException()
    }

    // TODO 2-2. 로컬에서 Checked Exception 발생 -> rollback : 안됨
    fun localThrowCheckedException() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        throwLocalCheckedException()
    }

    // TODO 4-1. local Runtime Exception 을 try-catch 로 잡을 경우 rollback 이 될까? - 안됨
    fun tryCatchLocalThrowRuntimeException() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        try {
            throwLocalRuntimeException()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    // TODO 4-2. local Checked Exception 을 try-catch 로 잡을 경우 rollback 이 될까? - 안됨
    fun tryCatchLocalThrowCheckedException() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        try {
            throwLocalCheckedException()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    // TODO 5-1. inner Runtime Exception 을 try-catch 로 잡을 경우 rollback 이 될까? - 안됨
    fun tryCatchInnerThrowRuntimeException() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        testInnerService.tryCatchRuntimeException()
    }

    // TODO 5-2. outer try-catch + inner 에서 RuntimeException 던짐 - 롤백 예외 발생(롤백 됨)
    // TODO 이 경우, 내부 서비스 메소드에 Transactional 붙어 있는지 여부에 차이가 발생
    // TODO 있으면 롤백 되면서 rollback 예외 발생, 없으면 정상 실행 되고 예외는 catch 되고 flush
    // 내부 트랜잭션이 있을 때, 내부 메서드에서 runtime exception 이 발생하면 rollback marking 이 되어 외부 트랜잭션에서도 롤백이 됨
    // DataSourceTransactionManager 을 직접 쓸 때는 내부 트랜잭션이 문제가 생겨도 외부 트랜잭션에는 영향이 없게 조정 할 수 있지만,
    // JtaTransactionManager 의 경우에는 내부 트랜잭션에서 문제가 생기면 외부 트랜잭션에도 영향을 받음(전파 레벨과 무관)

    // 내부 트랜잭션에서 try-catch 로 또 잡아주면 exception 발생 없이 롤백 되지 않음!
    fun tryCatchTwiceInnerThrowRuntimeException() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        try {
            testInnerService.throwRuntimeException()
        } catch (e: RuntimeException) {
            println(e.message)
        }
    }

    // TODO 5-3. inner Checked Exception 을 try-catch 로 잡을 경우 rollback 이 될까? - 안됨
    fun tryCatchInnerThrowCheckedException() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        testInnerService.tryCatchCheckedException()
    }

    // TODO 5-4. outer try-catch + inner 에서 Checked Exception 던짐 (롤백 안됨)
    fun tryCatchTwiceInnerThrowCheckedException() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        try {
            testInnerService.throwCheckedException()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    // TODO 6-1. more inner service 에서 Checked Exception 되는 경우 -> rollback : 안됨
    // TODO 내부 서비스 메서드 @Transactional 여부 확인 : 무관
    fun moreInnerChecked() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        testInnerService.moreInnerServiceCheckedException()
    }

    // TODO 6-2. more inner service 에서 Runtime Exception 되는 경우 -> rollback : 됨
    // TODO 내부 서비스 메서드 @Transactional 여부 확인 : 무관
    fun moreInnerRuntime() {
        testOuterRepository.save(TestOuterEntity(id = 1, content = "content 1"))
        testInnerService.moreInnerServiceRuntimeException()
    }

    // TODO 7-1. kotlin run 은 이전에 Checked Exception 터지면 그 다음 코드부터 실행이 안되는지! -> 안되는 거 확인 완료!
    fun kotlinRuntWithCheckedTest() {
        println("outer service 시작")
        testInnerService.kotlinRunWithCheckedExceptionTest()
    }

    // TODO 7-2. kotlin run 은 이전에 Runtime Exception 터지면 그 다음 코드부터 실행이 안되는지! -> 안되는 거 확인 완료!
    fun kotlinRunWithRuntimeTest() {
        println("outer service 시작")
        testInnerService.kotlinRunWithRuntimeExceptionTest()
    }

    private fun throwLocalRuntimeException() {
        throw RuntimeException("local runtime exception")
    }

    private fun throwLocalCheckedException() {
        throw Exception("local checked exception")
    }

}