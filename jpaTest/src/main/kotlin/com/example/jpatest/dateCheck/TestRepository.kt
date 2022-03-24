package com.example.jpatest.dateCheck

import com.example.jpatest.dateCheck.TestEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface TestRepository : JpaRepository<TestEntity, Long> {
    fun findAllByCreatedAtBetween(startDateTime: LocalDateTime, endDateTime: LocalDateTime) : List<TestEntity>
    fun findAllByUserIdAndCreatedAtBetween(userId: Long, startDateTime: LocalDateTime, endDateTime: LocalDateTime) : List<TestEntity>
    fun findFirstByUserIdOrderByCreatedAtDesc(userId: Long): TestEntity
}