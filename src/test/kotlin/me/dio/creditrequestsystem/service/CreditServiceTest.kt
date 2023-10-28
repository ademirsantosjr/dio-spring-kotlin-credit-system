package me.dio.creditrequestsystem.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import me.dio.creditrequestsystem.CustomerBuilder
import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.enumeration.Status
import me.dio.creditrequestsystem.repository.CreditRepository
import me.dio.creditrequestsystem.service.impl.CreditService
import me.dio.creditrequestsystem.service.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import kotlin.collections.List

// @ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {

    @MockK lateinit var creditRepository: CreditRepository
    @MockK lateinit var customerService: CustomerService
    @InjectMockKs lateinit var creditService: CreditService

    @Test
    fun `should create credit`() {
        // given
        val fakeCredit: Credit = buildCredit()
        val fakeCustomer: Customer = CustomerBuilder.build()
        every { customerService.findById(any()) } returns fakeCustomer
        every { creditRepository.save(any()) } returns fakeCredit
        // when
        val actual: Credit = creditService.save(fakeCredit)
        // then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.save(fakeCredit) }
    }

    @Test
    fun `should find credits of the given customer`() {
        // given
        val fakeCredit: Credit = buildCredit()
        val fakeCustomer: Customer = CustomerBuilder.build()
        every { creditRepository.findAllByCustomerId(any()) } returns mutableListOf(fakeCredit)
        // when
        val actualList: List<Credit> = creditService.findAllByCustomer(fakeCustomer.id!!)
        // then
        Assertions.assertThat(actualList).isNotEmpty
        Assertions.assertThat(actualList).contains(fakeCredit)
        verify(exactly = 1) { creditRepository.findAllByCustomerId(fakeCustomer.id!!) }
    }

    @Test
    fun `should find credit by code`() {
        // given
        val fakeCreditCode: UUID = UUID.randomUUID()
        val fakeCredit: Credit = buildCredit(creditCode = fakeCreditCode)
        val fakeCustomer: Customer = CustomerBuilder.build()
        every { creditRepository.findByCreditCode(any()) } returns fakeCredit
        // when
        val foundCredit: Credit = creditService.findByCreditCode(fakeCustomer.id!!, fakeCreditCode)
        // then
        Assertions.assertThat(foundCredit).isNotNull
        Assertions.assertThat(foundCredit).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.findByCreditCode(fakeCreditCode) }
    }

    private fun buildCredit(
            creditCode: UUID = UUID.randomUUID(),
            creditValue: BigDecimal = BigDecimal.valueOf(1500.0),
            dayFirstInstallment: LocalDate = LocalDate.now(),
            numberOfInstallment: Int = 12,
            status: Status = Status.IN_PROGRESS,
            customer: Customer = CustomerBuilder.build(),
            id: Long = 1L
    ) = Credit(
            id = id,
            creditCode = creditCode,
            creditValue = creditValue,
            dayFirstInstallment = dayFirstInstallment,
            numberOfInstallment = numberOfInstallment,
            status = status,
            customer = customer
    )
}