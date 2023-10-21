package me.dio.creditrequestsystem.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
        @field:NotNull(message = "Credit value may not be null.") val creditValue: BigDecimal,
        @field:Future(message = "Date of First Installment may not be int the past.")
        @field:NotNull(message = "Date of first installment may not be null.") val dayFirstOfInstallment: LocalDate,
        @field:Min(value = 1) @field:Max(value = 48) val numberOfInstallments: Int,
        @field:NotNull(message = "Customer ID may not be null.") val customerId: Long
) {
    fun toEntity(): Credit = Credit(
            creditValue = this.creditValue,
            dayFirstInstallment = this.dayFirstOfInstallment,
            numberOfInstallment = this.numberOfInstallments,
            customer = Customer(id = this.customerId)
    )
}

