package me.dio.creditrequestsystem.service.impl

import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.repository.CustomerRepository
import me.dio.creditrequestsystem.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository): ICustomerService {

    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow {
        throw RuntimeException("Id $id not found")
    }

    override fun deleteById(id: Long) = this.customerRepository.deleteById(id)

}