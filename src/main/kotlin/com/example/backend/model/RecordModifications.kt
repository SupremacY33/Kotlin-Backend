package com.example.backend.model

data class RecordModifications(
    val customerName: String,
    val customerEmail: String,
    val customerPassword: String,
    val customerConfirmPassword: String,
    val customerNoOfTaskCompleted: String,
    val customerAllTaskCompleted: Boolean
)
