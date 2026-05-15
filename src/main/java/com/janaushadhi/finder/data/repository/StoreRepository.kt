package com.janaushadhi.finder.data.repository

import com.janaushadhi.finder.data.model.Store
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor() {

    private val locationData = mapOf(
        // Areas
        "yelahanka" to LocationInfo(13.1006, 77.5963),
        "whitefield" to LocationInfo(12.9698, 77.7500),
        "indiranagar" to LocationInfo(12.9784, 77.6408),
        "electronic city" to LocationInfo(12.8452, 77.6632),
        
        // Colleges
        "svce" to LocationInfo(13.1295, 77.5877),
        "reva university" to LocationInfo(13.1136, 77.6358),
        "bmsit" to LocationInfo(13.1342, 77.5678),
        "rv college of engineering" to LocationInfo(12.9237, 77.4987),
        "ms ramaiah institute of technology" to LocationInfo(13.0311, 77.5649),
        "presidency university" to LocationInfo(13.1691, 77.5342),
        "jain university" to LocationInfo(12.9204, 77.5929),
        "pes university" to LocationInfo(12.9352, 77.5359)
    )

    fun getCenterForLocation(query: String): Pair<Double, Double>? {
        val lowerQuery = query.lowercase().trim()
        val match = locationData.entries.find { lowerQuery.contains(it.key) }
        return match?.value?.let { it.lat to it.lng }
    }

    fun getNearbyStores(lat: Double, lng: Double): List<Store> {
        // Generate 3-5 stores close to the provided coordinate
        return (1..4).map { i ->
            Store(
                id = "${lat}_${lng}_$i",
                name = "Jan Aushadhi Kendra - Store $i",
                latitude = lat + (Math.random() - 0.5) * 0.01, // Very close (within ~1km)
                longitude = lng + (Math.random() - 0.5) * 0.01,
                address = "Opposite Campus/Area Gate $i, Bengaluru, Karnataka",
                phone = "+91 80 2345 678$i",
                isOpen = Math.random() > 0.3,
                distance = "${String.format("%.1f", Math.random() * 2 + 0.5)} km"
            )
        }
    }
    
    private data class LocationInfo(val lat: Double, val lng: Double)
}
