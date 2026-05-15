package com.janaushadhi.finder.ui.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.janaushadhi.finder.BuildConfig
import com.janaushadhi.finder.data.model.Store
import com.janaushadhi.finder.data.remote.GeocodingApiService
import com.janaushadhi.finder.data.repository.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MapType {
    GOOGLE_MAPS, IMAGE_MAP
}

data class ImageMarker(
    val store: Store,
    val offsetX: Float, // 0.0 to 1.0
    val offsetY: Float  // 0.0 to 1.0
)

data class MapsUiState(
    val stores: List<Store> = emptyList(),
    val imageMarkers: List<ImageMarker> = emptyList(),
    val mapType: MapType = MapType.GOOGLE_MAPS,
    val mapImageRes: Int? = null,
    val searchQuery: String = "",
    val selectedStore: Store? = null,
    val cameraPosition: LatLng = LatLng(28.6139, 77.2090),
    val isSearching: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: StoreRepository,
    private val geocodingApiService: GeocodingApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapsUiState())
    val uiState: StateFlow<MapsUiState> = _uiState.asStateFlow()

    private val mapsApiKey = BuildConfig.MAPS_API_KEY

    // Mock data for image maps
    private val yelahankaMarkers = listOf(
        ImageMarker(
            Store("y1", "Pradhana Manthri Bhartiya Jan Aushadhi Kendra", 0.0, 0.0, "Yelahanka Old Town, Bangalore", "+91 98450 12345", true, "0.5 km"),
            0.35f, 0.08f
        ),
        ImageMarker(
            Store("y2", "Jan Aushadhi Kendra Maruthinagar", 0.0, 0.0, "Maruthi Nagar, Yelahanka, Bangalore", "+91 98450 67890", true, "1.2 km"),
            0.62f, 0.45f
        ),
        ImageMarker(
            Store("y3", "Pradhan Mantri Jan Aushadhi Kendra", 0.0, 0.0, "Attur Layout, Yelahanka, Bangalore", "+91 98450 54321", false, "2.1 km"),
            0.32f, 0.48f
        ),
        ImageMarker(
            Store("y4", "Jan Aushadhi Store", 0.0, 0.0, "Near Yelahanka Police Station, Bangalore", "+91 98450 11223", true, "0.8 km"),
            0.53f, 0.54f
        )
    )

    private val whitefieldMarkers = listOf(
        ImageMarker(
            Store("w1", "Jan Aushadhi Store - Whitefield", 0.0, 0.0, "ITPL Main Road, Whitefield", "+91 99000 12345", true, "0.4 km"),
            0.45f, 0.35f
        ),
        ImageMarker(
            Store("w2", "Jan Aushadhi Kendra Kadugodi", 0.0, 0.0, "Kadugodi, Whitefield, Bangalore", "+91 99000 54321", true, "1.8 km"),
            0.75f, 0.65f
        )
    )

    init {
        loadStores(28.6139, 77.2090)
    }

    private fun loadStores(lat: Double, lng: Double) {
        val stores = repository.getNearbyStores(lat, lng)
        _uiState.update { it.copy(stores = stores) }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun performSearch() {
        val query = _uiState.value.searchQuery
        if (query.isBlank()) return

        // Handle custom image map areas
        if (query.equals("Yelahanka", ignoreCase = true)) {
            _uiState.update { it.copy(
                mapType = MapType.IMAGE_MAP,
                imageMarkers = yelahankaMarkers,
                selectedStore = null
            )}
            return
        }

        if (query.equals("Whitefield", ignoreCase = true)) {
            _uiState.update { it.copy(
                mapType = MapType.IMAGE_MAP,
                imageMarkers = whitefieldMarkers,
                selectedStore = null
            )}
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSearching = true, errorMessage = null) }
            
            val demoLocation = repository.getCenterForLocation(query)
            if (demoLocation != null) {
                val latLng = LatLng(demoLocation.first, demoLocation.second)
                _uiState.update { it.copy(
                    cameraPosition = latLng,
                    mapType = MapType.GOOGLE_MAPS,
                    isSearching = false
                )}
                loadStores(demoLocation.first, demoLocation.second)
                return@launch
            }

            try {
                val response = geocodingApiService.geocode(query, mapsApiKey)
                if (response.status == "OK") {
                    val location = response.results.firstOrNull()?.geometry?.location
                    if (location != null) {
                        val latLng = LatLng(location.lat, location.lng)
                        _uiState.update { it.copy(
                            cameraPosition = latLng,
                            mapType = MapType.GOOGLE_MAPS
                        )}
                        loadStores(location.lat, location.lng)
                    } else {
                        _uiState.update { it.copy(errorMessage = "No results found for this area.") }
                    }
                } else {
                    _uiState.update { it.copy(errorMessage = "Maps Error: ${response.status}") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = "Network Error: ${e.message}") }
            } finally {
                _uiState.update { it.copy(isSearching = false) }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun searchThisArea(latLng: LatLng) {
        _uiState.update { it.copy(cameraPosition = latLng, mapType = MapType.GOOGLE_MAPS) }
        loadStores(latLng.latitude, latLng.longitude)
    }

    fun selectStore(store: Store?) {
        _uiState.update { it.copy(selectedStore = store) }
    }
}
