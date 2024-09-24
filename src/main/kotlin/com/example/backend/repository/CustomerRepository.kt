package com.example.backend.repository

import com.example.backend.entity.CustomerEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface CustomerRepository: MongoRepository<CustomerEntity, String> {

    fun findCustomerEntityByCustomerEmail( customerEmail: String ): Optional<CustomerEntity>

    fun findCustomerEntityByCustomerName( customerName: String ): List<CustomerEntity>

    fun findCustomerEntityById( id: String ): List<CustomerEntity>
}