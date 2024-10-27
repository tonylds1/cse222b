package org.example

import org.example.adapters.SQLitePassengerRepository
import org.example.application.TravelOrganizerApplication

fun main() {
   val tripOrganizerAPP = TravelOrganizerApplication()

   tripOrganizerAPP.createBusesList()
   tripOrganizerAPP.scheduleTrip()
   tripOrganizerAPP.addPassengersToTrip()
   tripOrganizerAPP.removeUnpaidPassengers()
   tripOrganizerAPP.startTrip()
   tripOrganizerAPP.finish()
}
