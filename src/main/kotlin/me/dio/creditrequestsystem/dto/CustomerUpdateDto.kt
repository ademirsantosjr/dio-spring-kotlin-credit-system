package me.dio.creditrequestsystem.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import me.dio.creditrequestsystem.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(
        @field:NotBlank(message = "First name may not be blank") val firstName: String,
        @field:NotBlank(message = "Last name may not be blank") val lastName: String,
        @field:NotNull(message = "Income may not be null") val income: BigDecimal,
        @field:NotBlank(message = "Zipcode may not be blank") val zipCode: String,
        @field:NotBlank(message = "Street may not be blank") val street: String
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return customer
    }
}
