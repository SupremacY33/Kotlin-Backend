package com.example.backend.service

import com.example.backend.entity.CustomerEntity
import com.example.backend.model.CustomerDetailsThroughCustomerId
import com.example.backend.profileMapping.mapToCustomerInitialization
import com.example.backend.profileMapping.mapToRetrievingCustomerList
import com.example.backend.profileMapping.mapToRetrievingCustomerListThroughName
import com.example.backend.model.RetrievingCustomerRecords
import com.example.backend.model.RetrievingCustomerThroughName
import com.example.backend.profileMapping.mapToCustomerDetailsThroughId
import com.example.backend.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService @Autowired constructor(
    private val customerRepository: CustomerRepository
) {

    fun customerProfileCreation(
        customerName: String,
        customerEmail: String,
        customerPassword: String,
        customerConfirmPassword: String,
        customerNoOfTaskCompleted: String,
        customerAllTaskCompleted: Boolean
    ) {
        val profileInquiry = customerRepository.findCustomerEntityByCustomerEmail(
            customerEmail
        ).isPresent

        if (profileInquiry == true) {
            throw IllegalStateException(
                "Profile Already Exists For Provided Email: $customerEmail"
            )
        } else {
            val profileOnboardingMapping = mapToCustomerInitialization(
                CustomerEntity(
                    id = null,
                    customerName,
                    customerEmail,
                    customerPassword,
                    customerConfirmPassword,
                    customerNoOfTaskCompleted,
                    customerAllTaskCompleted
                )
            )

            println(
                "Confirming customerProfileDetails: $profileOnboardingMapping"
            )

            customerRepository.save(
                profileOnboardingMapping
            )
        }
    }

    fun accessCustomerRecords()
    : List<RetrievingCustomerRecords> {

        val getClientRecords = customerRepository.findAll().toList()

        return mapToRetrievingCustomerList(
            getClientRecords
        )
    }

    fun customerProfileModification(
        id: String,
        customerName: String,
        customerEmail: String,
        customerPassword: String,
        customerConfirmPassword: String,
        customerNoOfTaskCompleted: String,
        customerAllTaskCompleted: Boolean
    ) {
        val adjustCustomerRecord = customerRepository.findById(id).orElseThrow {
            throw IllegalStateException(
                "Customer Records Doesn't Hold The Provided customerId: $id"
            )
        }

        adjustCustomerRecord.customerName = customerName
        adjustCustomerRecord.customerEmail = customerEmail
        adjustCustomerRecord.customerPassword = customerPassword
        adjustCustomerRecord.customerConfirmPassword = customerConfirmPassword
        adjustCustomerRecord.customerNoOfTaskCompleted = customerNoOfTaskCompleted
        adjustCustomerRecord.customerAllTaskCompleted = customerAllTaskCompleted

        customerRepository.save(
            adjustCustomerRecord
        )
    }

    fun customerRecordWiped(
        id: String
    ) {
        if (!customerRepository.existsById(id)) {
            throw IllegalStateException(
                "Provided customerId: $id, doesn't exists in any customer record"
            )
        }

        customerRepository.deleteById(id)
    }

    fun accessCustomerRecordsThroughName(
        customerName: String
    ): List<RetrievingCustomerThroughName> {

        val scanningCustomerRecords = customerRepository.findCustomerEntityByCustomerName(
            customerName
        )

        return mapToRetrievingCustomerListThroughName(
            scanningCustomerRecords
        )
    }

    fun findCustomerRecordThroughId(
        id: String
    ): List< CustomerDetailsThroughCustomerId > {

        val optionalResult = customerRepository.findCustomerEntityById(
            id
        )

        return mapToCustomerDetailsThroughId(
            optionalResult
        )

    }
}