package com.example.jpatest.extenstion

import org.springframework.data.repository.CrudRepository

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrowCheckedException(id: ID): T {
    return findById(id).orElseThrow { Exception() }
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrowRuntimeException(id: ID): T {
    return findById(id).orElseThrow { RuntimeException() }
}