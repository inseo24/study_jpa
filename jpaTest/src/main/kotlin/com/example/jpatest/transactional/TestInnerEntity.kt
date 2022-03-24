package com.example.jpatest.transactional

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class TestInnerEntity(
    @Id
    val id: Long,
    val content: String
) {
}