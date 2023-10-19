package me.dio.creditrequestsystem.controller

import me.dio.creditrequestsystem.dto.CustomerDto
import me.dio.creditrequestsystem.dto.CustomerUpdateDto
import me.dio.creditrequestsystem.dto.CustomerView
import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.service.impl.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustumerResource(
        private val customerService: CustomerService
) {

    @PostMapping
    fun save(@RequestBody customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer: Customer = this.customerService.save(customerDto.toEntity())
        val response: String = "Customer ${savedCustomer.email} saved with success!"
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val foundCustomer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(foundCustomer))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = customerService.deleteById(id)

    @PatchMapping
    fun updateById(@RequestParam(value = "customerId") id: Long,
                   @RequestBody customerUpdateDto: CustomerUpdateDto): ResponseEntity<CustomerView> {

        val customer: Customer = this.customerService.findById(id)
        val customerToUpdate: Customer = customerUpdateDto.toEntity(customer)
        val updatedCustomer: Customer = this.customerService.save(customerToUpdate)

        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(updatedCustomer))
    }
}