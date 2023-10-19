package me.dio.creditrequestsystem.dto

import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.enumeration.Status
import java.math.BigDecimal
import java.util.UUID

data class CreditView(
        val creditCode: UUID,
        val creditValue: BigDecimal,
        val numberOfInstallment: Int,
        val status: Status,
        val emailCustomer: String?,
        val incomeCustomer: BigDecimal?
) {
    constructor(credit: Credit): this(
            creditCode = credit.creditCode,
            creditValue = credit.creditValue,
            numberOfInstallment = credit.numberOfInstallment,
            status = credit.status,
            emailCustomer = credit.customer?.email,
            incomeCustomer = credit.customer?.income
    )
}
