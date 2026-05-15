package com.janaushadhi.finder.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.repository.MedicineRepository
import com.janaushadhi.finder.domain.model.Medicine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Success(val medicines: List<Medicine>, val query: String) : SearchUiState()
    data class Empty(val query: String) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MedicineRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isDbReady = MutableStateFlow(false)
    val isDbReady: StateFlow<Boolean> = _isDbReady.asStateFlow()

    private var debounceJob: Job? = null

    init {
        initializeDatabase()
    }

    private fun initializeDatabase() {
        viewModelScope.launch {
            try {
                repository.seedIfEmpty()
                _isDbReady.value = true
                // Load initial medicines
                performSearch("")
            } catch (e: Exception) {
                _uiState.value = SearchUiState.Error("Failed to load medicine database: ${e.message}")
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        debounceJob?.cancel()
        debounceJob = viewModelScope.launch {
            delay(300L) // 300ms debounce
            performSearch(query)
        }
    }

    fun onSearchSubmit() {
        debounceJob?.cancel()
        viewModelScope.launch {
            performSearch(_searchQuery.value)
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        debounceJob?.cancel()
        viewModelScope.launch {
            performSearch("")
        }
    }

    private suspend fun performSearch(query: String) {
        _uiState.value = SearchUiState.Loading
        try {
            val results = repository.searchMedicines(query)
            _uiState.value = if (results.isEmpty()) {
                SearchUiState.Empty(query)
            } else {
                SearchUiState.Success(results, query)
            }
        } catch (e: Exception) {
            _uiState.value = SearchUiState.Error(e.message ?: "Unknown error occurred")
        }
    }

    fun dismissError() {
        if (_uiState.value is SearchUiState.Error) {
            _uiState.value = SearchUiState.Idle
        }
    }
}
