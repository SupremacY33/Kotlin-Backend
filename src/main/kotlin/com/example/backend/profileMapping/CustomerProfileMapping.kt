package com.example.backend.profileMapping

import com.example.backend.entity.CustomerEntity
import com.example.backend.model.CustomerDetailsThroughCustomerId
import com.example.backend.model.RetrievingCustomerRecords
import com.example.backend.model.RetrievingCustomerThroughName

fun mapToCustomerInitialization(
    customerEntity: CustomerEntity
): CustomerEntity {
    return CustomerEntity(
        customerName = customerEntity.customerName,
        customerEmail = customerEntity.customerEmail,
        customerPassword = customerEntity.customerPassword,
        customerConfirmPassword = customerEntity.customerConfirmPassword,
        customerNoOfTaskCompleted = customerEntity.customerNoOfTaskCompleted,
        customerAllTaskCompleted = customerEntity.customerAllTaskCompleted
    )
}

fun mapToRetrievingCustomerList(
    customerEntity: List<CustomerEntity>
): List<RetrievingCustomerRecords> {
    return customerEntity.map { customerEntities ->
        RetrievingCustomerRecords(
            id = customerEntities.id ?: "null",
            customerName = customerEntities.customerName,
            customerEmail = customerEntities.customerEmail,
            customerPassword = customerEntities.customerPassword,
            customerConfirmPassword = customerEntities.customerConfirmPassword,
            customerNoOfTaskCompleted = customerEntities.customerNoOfTaskCompleted,
            customerAllTaskCompleted = customerEntities.customerAllTaskCompleted
        )
    }
}

fun mapToRetrievingCustomerListThroughName(
    customerEntity: List<CustomerEntity>
): List<RetrievingCustomerThroughName> {
    return customerEntity.map { entity ->
        RetrievingCustomerThroughName(
            customerName = entity.customerName,
            customerEmail = entity.customerEmail,
            customerPassword = entity.customerPassword,
            customerConfirmPassword = entity.customerConfirmPassword,
            customerNoOfTaskCompleted = entity.customerNoOfTaskCompleted,
            customerAllOfTaskCompleted = entity.customerAllTaskCompleted
        )
    }
}

fun mapToCustomerDetailsThroughId(
    customerEntity: List<CustomerEntity >
): List< CustomerDetailsThroughCustomerId > {

    return customerEntity.map { myEntity ->
        CustomerDetailsThroughCustomerId(
            customerName = myEntity.customerName,
            customerEmail = myEntity.customerEmail,
            customerPassword = myEntity.customerPassword
        )
    }

}