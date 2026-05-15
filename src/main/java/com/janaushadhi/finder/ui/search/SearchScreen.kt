package com.janaushadhi.finder.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.janaushadhi.finder.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isDbReady by viewModel.isDbReady.collectAsState()
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    // Show error snackbar
    LaunchedEffect(uiState) {
        if (uiState is SearchUiState.Error) {
            val result = snackbarHostState.showSnackbar(
                message = (uiState as SearchUiState.Error).message,
                actionLabel = "Dismiss",
                duration = SnackbarDuration.Long
            )
            if (result == SnackbarResult.ActionPerformed) {
                viewModel.dismissError()
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                    actionColor = MaterialTheme.colorScheme.error
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Jan Aushadhi",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Medicine Price Finder",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            letterSpacing = 0.5.sp
                        )
                    }
                },
                actions = {
                    // DB status indicator
                    if (!isDbReady) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp).padding(end = 16.dp),
                            strokeWidth = 2.dp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Search bar
            SearchBar(
                query = searchQuery,
                onQueryChange = viewModel::onSearchQueryChange,
                onSearch = viewModel::onSearchSubmit,
                onClear = viewModel::clearSearch
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Results count
            AnimatedVisibility(visible = uiState is SearchUiState.Success) {
                val count = (uiState as? SearchUiState.Success)?.medicines?.size ?: 0
                val query = (uiState as? SearchUiState.Success)?.query ?: ""
                Text(
                    text = if (query.isBlank()) "Showing top $count medicines"
                           else "$count results for \"$query\"",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            // Content area
            Box(modifier = Modifier.fillMaxSize()) {
                when (val state = uiState) {
                    is SearchUiState.Loading -> {
                        if (searchQuery.isBlank()) {
                            // Show shimmer cards for initial load
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                contentPadding = PaddingValues(vertical = 8.dp)
                            ) {
                                items(5) {
                                    LoadingShimmerCard()
                                }
                            }
                        } else {
                            LoadingState(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    is SearchUiState.Success -> {
                        LazyColumn(
                            state = listState,
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 0.dp)
                        ) {
                            itemsIndexed(
                                items = state.medicines,
                                key = { _, medicine -> medicine.id }
                            ) { index, medicine ->
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = true,
                                    enter = fadeIn(animationSpec = tween(200, delayMillis = index * 30)) +
                                            slideInVertically(
                                                initialOffsetY = { it / 4 },
                                                animationSpec = tween(200, delayMillis = index * 30)
                                            )
                                ) {
                                    MedicineCard(medicine = medicine)
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.height(80.dp))
                            }
                        }
                    }

                    is SearchUiState.Empty -> {
                        EmptyState(
                            query = state.query,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    is SearchUiState.Idle -> {
                        IdleState(modifier = Modifier.align(Alignment.Center))
                    }

                    is SearchUiState.Error -> {
                        EmptyState(
                            query = "",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}
