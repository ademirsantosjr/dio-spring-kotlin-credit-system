package me.dio.creditrequestsystem

import me.dio.creditrequestsystem.entity.Address
import me.dio.creditrequestsystem.entity.Customer
import java.math.BigDecimal

class CustomerBuilder {

    companion object {
        fun build(
                firstName: String = "Maria",
                lastName: String = "De Jesus",
                cpf: String = "28475934625",
                email: String = "maria_jesus@gmail.com",
                password: String = "123456",
                zipCode: String = "85005700",
                street: String = "Rua das Águas",
                income: BigDecimal = BigDecimal.valueOf(1000.0),
                id: Long = 1L
        ) = Customer(
                firstName = firstName,
                lastName = lastName,
                cpf = cpf,
                email = email,
                password = password,
                address = Address(
                        zipCode = zipCode,
                        street = street
                ),
                income = income,
                id = id
        )
    }
}