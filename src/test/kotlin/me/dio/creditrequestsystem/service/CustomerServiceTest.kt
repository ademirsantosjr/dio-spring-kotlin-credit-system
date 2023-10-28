package me.dio.creditrequestsystem.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.dio.creditrequestsystem.entity.Address
import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.exception.BusinessException
import me.dio.creditrequestsystem.repository.CustomerRepository
import me.dio.creditrequestsystem.service.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random

// @ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `should create customer`() {
        // given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer
        // when
        val actual: Customer = customerService.save(fakeCustomer)
        // then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun `should find customer by id`() {
        // given
        val fakedId: Long = Random.nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakedId)
        every {customerRepository.findById(fakedId)} returns Optional.of(fakeCustomer)
        // when
        val actual: Customer = customerService.findById(fakedId)
        // then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakedId) }
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`() {
        // give
        val fakeId: Long = Random.nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()
        // when
        // then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
                .isThrownBy { customerService.findById(fakeId) }
                .withMessage("Id $fakeId not found")
    }

    @Test
    fun `should delete customer by id`() {
        // given
        val fakeId: Long = Random.nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs
        // when
        customerService.deleteById(fakeId)
        // then
        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }

    private fun buildCustomer(
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