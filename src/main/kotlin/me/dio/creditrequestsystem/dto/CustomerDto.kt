package me.dio.creditrequestsystem.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import me.dio.creditrequestsystem.entity.Address
import me.dio.creditrequestsystem.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
        @field:NotBlank(message = "Name may not be blank.") val firstName: String,
        @field:NotBlank(message = "Last name may not be blank.") val lastName: String,
        @field:NotBlank(message = "CPF may not be blank.")
        @field:CPF(message = "Invalid CPF.") val cpf: String,
        @field:NotNull(message = "Income may not be null.") val income: BigDecimal,
        @field:NotBlank(message = "Email may not be blank.") val email: String,
        @field:NotBlank(message = "Password may not be blank.") val password: String,
        @field:NotBlank(message = "Zipcode may not be blank.") val zipCode: String,
        @field:NotBlank(message = "Street may not be blank.") val street: String
) {
    fun toEntity(): Customer = Customer(
            firstName = this.firstName,
            lastName = this.lastName,
            cpf = this.cpf,
            income = this.income,
            email = this.email,
            password = this.password,
            address = Address(
                    zipCode = this.zipCode,
                    street = this.street
            )
    )
}
