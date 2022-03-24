package com.example.jpatest.dateCheck

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "test_entity")
class TestEntity(
    @Id
    val id: Long? = null,
    var createdAt: LocalDateTime? = null,
    val content: String,
    val userId: Long,
) {

}