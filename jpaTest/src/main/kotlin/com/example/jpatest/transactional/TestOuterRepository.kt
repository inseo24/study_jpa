package com.example.jpatest.transactional

import org.springframework.data.jpa.repository.JpaRepository

interface TestOuterRepository: JpaRepository<TestOuterEntity, Long> {
}