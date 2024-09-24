package com.example.backend.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "CustomerInitializationDatabase")
data class CustomerEntity(

    @Id
    val id: String? = null,
    var customerName: String,
    var customerEmail: String,
    var customerPassword: String,
    var customerConfirmPassword: String,
    var customerNoOfTaskCompleted: String,
    var customerAllTaskCompleted: Boolean
)
