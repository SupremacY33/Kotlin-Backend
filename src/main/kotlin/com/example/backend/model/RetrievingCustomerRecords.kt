package com.example.backend.model

data class RetrievingCustomerRecords(
    val id: String,
    val customerName: String,
    val customerEmail: String,
    val customerPassword: String,
    val customerConfirmPassword: String,
    val customerNoOfTaskCompleted: String,
    val customerAllTaskCompleted: Boolean
)
