package com.pub.cc.skyjourney.core.controller

import com.pub.cc.skyjourney.core.model.Flight
import com.pub.cc.skyjourney.core.service.FlightService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping(
    path = ["/flights"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@CrossOrigin(origins = ["*"])
class FlightController(
    private val flightService: FlightService
) {

    @GetMapping
    fun getFlights(): ResponseEntity<List<Flight>> {
        val flights = flightService.getFlights()

        return ResponseEntity.ok(flights)
    }

    @GetMapping("/search")
    fun search(
        @RequestParam origin: String,
        @RequestParam destination: String,
        @RequestParam airlines: List<String>?,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate?,
    ): ResponseEntity<List<Flight>> {
        val flights = flightService.getFilteredFlights(origin, destination, airlines, date)

        return ResponseEntity.ok(flights)
    }

    @GetMapping("/{flightId}")
    fun search(
        @PathVariable flightId: String
    ): ResponseEntity<Flight> {
        val flight = flightService.getFlightById(flightId)

        return ResponseEntity.ok(flight)
    }
}