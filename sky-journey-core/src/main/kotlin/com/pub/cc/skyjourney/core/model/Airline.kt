package com.pub.cc.skyjourney.core.model

enum class Airline(private val airlineName: String) {
    EMIRATES("Emirates"),
    QANTAS("Qantas"),
    LUFTHANSA("Lufthansa"),
    DELTA("Delta Air Lines"),
    UNITED("United Airlines"),
    AIR_CANADA("Air Canada"),
    BRITISH_AIRWAYS("British Airways"),
    AIR_FRANCE("Air France"),
    AMERICAN_AIRLINES("American Airlines"),
    SINGAPORE_AIRLINES("Singapore Airlines");

    override fun toString(): String {
        return airlineName
    }

    companion object {
        fun fromName(airlines: List<String>?): List<Airline> {
            if (airlines == null) {
                return  Airline.values().toList()
            }

            return airlines.map { airline ->
                Airline.values().first { it.name == airline || it.airlineName == airline }
            }
        }
    }
}
