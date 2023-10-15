package me.dio.creditrequestsystem.repository

import me.dio.creditrequestsystem.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface CreditRepository: JpaRepository<Credit, Long> {

    fun findByCreditCode(code: UUID): Credit?

    @Query(value = "SELECT * FROM credits WHERE customer_id = ?1", nativeQuery = true)
    fun findAllByCustomerId(customerId: Long): List<Credit>
}