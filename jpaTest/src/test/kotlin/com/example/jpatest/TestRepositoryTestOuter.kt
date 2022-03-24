package com.example.jpatest

import com.example.jpatest.dateCheck.TestEntity
import com.example.jpatest.dateCheck.TestRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@SpringBootTest
class TestRepositoryTestOuter {

    @Autowired
    lateinit var testRepository: TestRepository

    @Test
    fun testLocalDateTime() {
        testRepository.saveAll(
            listOf(
                TestEntity(id = 1, createdAt = LocalDateTime.now(), content = "컨텐트1", userId = 1),
                TestEntity(id = 2, createdAt = LocalDateTime.now().minusDays(1), content = "컨텐트2", userId = 1),
                TestEntity(id = 3, createdAt = LocalDateTime.now(), content = "컨텐트3", userId = 1),
                TestEntity(id = 4, createdAt = LocalDateTime.now().minusHours(2), content = "컨텐트4", userId = 1),
            )
        )

        println(LocalDateTime.now())
        println(LocalDateTime.now().minusHours(1))
        println(LocalDateTime.now().minusHours(2))
        println()

        val startDateTime: LocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now().minusHours(1))
        val endDateTime: LocalDateTime = LocalDateTime.now()
        val response = testRepository.findAllByCreatedAtBetween(startDateTime, endDateTime)
        println(response)
        println(response.map {
            it.content
        })
    }


    // TODO 당일(1일) 생성된 것 조회
    @Test
    fun todayDataTest() {

        testRepository.saveAll(
            listOf(
                TestEntity(id = 1, createdAt = LocalDateTime.now(), content = "컨텐트1", userId = 1),
                TestEntity(id = 2, createdAt = LocalDateTime.now().minusDays(1), content = "컨텐트2", userId = 1),
                TestEntity(id = 3, createdAt = LocalDateTime.now().minusHours(2), content = "컨텐트3", userId = 1),
                TestEntity(id = 4, createdAt = LocalDateTime.now().minusHours(16), content = "컨텐트4", userId = 2),
                TestEntity(id = 5, createdAt = LocalDateTime.now().minusHours(6), content = "컨텐트5", userId = 2),
                TestEntity(id = 6, createdAt = LocalDateTime.now().minusHours(1), content = "컨텐트6", userId = 2),
                TestEntity(id = 7, createdAt = LocalDateTime.now().minusMinutes(30), content = "컨텐트7", userId = 2),
            )
        )

        println()
        println(LocalDateTime.now().minusHours(15))
        println()

        val startDateTime: LocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0))
        val endDateTime: LocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59))

        println(startDateTime)
        println(endDateTime)
        println()

        val response = testRepository.findAllByCreatedAtBetween(startDateTime, endDateTime)

        val todayUserData = testRepository.findAllByUserIdAndCreatedAtBetween(userId = 1, startDateTime, endDateTime)

        println(response.map {
            it.content
        })

        println(todayUserData.map {
            it.userId
            it.createdAt
            it.content
        })
        println(todayUserData.size)

    }


    // TODO 현재부터 1시간 이내 생성된 것들 조회
    @Test
    fun dataIn1Hour() {

        testRepository.saveAll(
            listOf(
                TestEntity(id = 1, createdAt = LocalDateTime.now(), content = "컨텐트1", userId = 1),
                TestEntity(id = 2, createdAt = LocalDateTime.now().minusDays(1), content = "컨텐트2", userId = 1),
                TestEntity(id = 3, createdAt = LocalDateTime.now().minusHours(2), content = "컨텐트3", userId = 1),
                TestEntity(id = 4, createdAt = LocalDateTime.now().minusHours(16), content = "컨텐트4", userId = 1),
                TestEntity(id = 5, createdAt = LocalDateTime.now().minusHours(6), content = "컨텐트5", userId = 1),
                TestEntity(id = 6, createdAt = LocalDateTime.now().minusHours(1), content = "컨텐트6", userId = 2),
                TestEntity(id = 7, createdAt = LocalDateTime.now().minusMinutes(30), content = "컨텐트7", userId = 2),
                TestEntity(id = 8, createdAt = LocalDateTime.now().minusMinutes(15), content = "컨텐트8", userId = 2),
                TestEntity(id = 9, createdAt = LocalDateTime.now().minusMinutes(60), content = "컨텐트9", userId = 2),
                TestEntity(id = 10, createdAt = LocalDateTime.now().minusHours(1), content = "컨텐트10", userId = 2),
                TestEntity(id = 11, createdAt = LocalDateTime.now().minusMinutes(59), content = "컨텐트11", userId = 3),
            )
        )

        println()

        val startDateTime: LocalDateTime = LocalDateTime.now().minusHours(1)
        val endDateTime: LocalDateTime = LocalDateTime.now()

        println(startDateTime)
        println(endDateTime)
        println()

        val response = testRepository.findAllByCreatedAtBetween(startDateTime, endDateTime)

        val user1Response = testRepository.findAllByUserIdAndCreatedAtBetween(userId = 1, startDateTime, endDateTime)
        val user2Response = testRepository.findAllByUserIdAndCreatedAtBetween(userId = 2, startDateTime, endDateTime)
        val user3Response = testRepository.findAllByUserIdAndCreatedAtBetween(userId = 3, startDateTime, endDateTime)

        println("whole data")
        println(response.map {
            it.content
        })

        println("user1")
        println(user1Response.map {
            it.content
        })

        println("user2")
        println(user2Response.map {
            it.content
        })

        println("user3")
        println(user3Response.map {
            it.content
        })
    }

    // TODO 한 유저의 가장 최근 요청 데이터 1개 출력
    @Test
    fun recentDataWithUser() {

        testRepository.saveAll(
            listOf(
                TestEntity(id = 1, createdAt = LocalDateTime.now().minusMinutes(2), content = "컨텐트1", userId = 1),
                TestEntity(id = 2, createdAt = LocalDateTime.now().minusDays(1), content = "컨텐트2", userId = 1),
                TestEntity(id = 3, createdAt = LocalDateTime.now().minusHours(2), content = "컨텐트3", userId = 1),
                TestEntity(id = 4, createdAt = LocalDateTime.now().minusHours(16), content = "컨텐트4", userId = 1),
                TestEntity(id = 5, createdAt = LocalDateTime.now().minusHours(6), content = "컨텐트5", userId = 1),

                TestEntity(id = 6, createdAt = LocalDateTime.now().minusHours(1), content = "컨텐트6", userId = 2),
                TestEntity(id = 7, createdAt = LocalDateTime.now().minusMinutes(30), content = "컨텐트7", userId = 2),
                TestEntity(id = 8, createdAt = LocalDateTime.now().minusMinutes(15), content = "컨텐트8", userId = 2),
                TestEntity(id = 9, createdAt = LocalDateTime.now().minusMinutes(60), content = "컨텐트9", userId = 2),
                TestEntity(id = 10, createdAt = LocalDateTime.now().minusHours(1), content = "컨텐트10", userId = 2),

                TestEntity(id = 11, createdAt = LocalDateTime.now().minusMinutes(66), content = "컨텐트11", userId = 3),
            )
        )

        println()
        // TODO where + order by + limit
        // TODO 1) where 조건 엔티티 검색 2) select * data 3) order by 4) limit
        val user1Response = testRepository.findFirstByUserIdOrderByCreatedAtDesc(userId = 1)
        val user2Response = testRepository.findFirstByUserIdOrderByCreatedAtDesc(userId = 2)
        val user3Response = testRepository.findFirstByUserIdOrderByCreatedAtDesc(userId = 3)

        println("now time")
        println(LocalDateTime.now())

        println("user1")
        println(user1Response.userId)
        println(user1Response.createdAt)
        println(user1Response.content)

        println("1시간 전 확인")
        // 15:56 isBefore 14:56 -> false -> 정상 케이스(예외 x)
        println(user1Response.createdAt!!.isBefore(LocalDateTime.now().minusHours(1)))

        println("user2")
        println(user2Response.userId)
        println(user2Response.createdAt)
        println(user2Response.content)

        println("user3")
        println(user3Response.userId)
        println(user3Response.createdAt)
        println(user3Response.content)

        println("1시간 전 확인")
        // 14:43 isBefore 14:58 -> true -> 예외 케이스(예외 o)
        println(user3Response.createdAt!!.isBefore(LocalDateTime.now().minusHours(1)))

    }

}