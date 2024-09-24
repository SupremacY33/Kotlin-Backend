package com.example.backend.controller

import com.example.backend.model.CustomerInitialization
import com.example.backend.model.RetrievingCustomerRecords
import com.example.backend.model.RetrievingCustomerThroughName
import com.example.backend.model.RecordModifications
import com.example.backend.model.CustomerDetailsThroughCustomerId
import com.example.backend.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contactDetails")
class CustomerController @Autowired constructor(
    private val customerService: CustomerService
) {

    @PostMapping("/clientInitialization")
    fun customerOnboarding(
        @RequestBody customerInitialization: CustomerInitialization
    ): ResponseEntity<String> {

        customerService.customerProfileCreation(
            customerInitialization.customerName,
            customerInitialization.customerEmail,
            customerInitialization.customerPassword,
            customerInitialization.customerConfirmPassword,
            customerInitialization.customerNoOfTaskCompleted,
            customerInitialization.customerAllTaskCompleted
        )

        val response200Message = "Client Onboarding Successfully Completed"

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response200Message)
    }

    @GetMapping("/RetrievingRecords")
    fun retrieveCustomerCompleteRecords()
    : ResponseEntity<List<RetrievingCustomerRecords>> {

        val requiredResult = customerService.accessCustomerRecords()

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(requiredResult)
    }

    @PutMapping("/ModifyingRecords/{id}")
    fun customerRecordModification(
        @PathVariable id: String,
        @RequestBody recordModifications: RecordModifications
    ): ResponseEntity<String> {

        customerService.customerProfileModification(
            id,
            recordModifications.customerName,
            recordModifications.customerEmail,
            recordModifications.customerPassword,
            recordModifications.customerConfirmPassword,
            recordModifications.customerNoOfTaskCompleted,
            recordModifications.customerAllTaskCompleted
        )

        val modified200Response = "Customer Profile Modification Completed Successfully"

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(modified200Response)
    }

    @DeleteMapping("/EraseCustomerRecord/{id}")
    fun customerRecordDeletion(
        @PathVariable id: String
    ): ResponseEntity<String> {

        customerService.customerRecordWiped(
            id
        )

        val deletion200Response = "Deletion Of Customer Record Was Successful"

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(deletion200Response)
    }

    @GetMapping("/RetrievingCustomerRecord/{customerName}")
    fun retrievingCustomerRecordThroughName(
        @PathVariable customerName: String
    ): ResponseEntity<List<RetrievingCustomerThroughName>> {

        val resultThroughCustomerName = customerService.accessCustomerRecordsThroughName(
            customerName
        )

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(resultThroughCustomerName)
    }

    @GetMapping("/CustomerRecordThroughId/{id}")
    fun gatherCustomerDetailsThroughCustomerId(
        @PathVariable id: String
    ): ResponseEntity< List< CustomerDetailsThroughCustomerId > > {

        return ResponseEntity.ok(
            this.customerService.findCustomerRecordThroughId(
                id
            )
        )

    }
}