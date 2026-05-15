package com.janaushadhi.finder.ui.maps

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.janaushadhi.finder.data.model.Store

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen(
    viewModel: MapsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(uiState.cameraPosition, 12f)
    }

    LaunchedEffect(uiState.selectedStore) {
        showBottomSheet = uiState.selectedStore != null
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    LaunchedEffect(uiState.cameraPosition) {
        if (uiState.mapType == MapType.GOOGLE_MAPS) {
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(uiState.cameraPosition, 14f)
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            
            // 1. Map Layer
            Crossfade(targetState = uiState.mapType, animationSpec = tween(500), label = "MapTransition") { type ->
                when (type) {
                    MapType.GOOGLE_MAPS -> {
                        GoogleMap(
                            modifier = Modifier.fillMaxSize(),
                            cameraPositionState = cameraPositionState,
                            uiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = true),
                            properties = MapProperties(isMyLocationEnabled = false)
                        ) {
                            uiState.stores.forEach { store ->
                                Marker(
                                    state = MarkerState(position = LatLng(store.latitude, store.longitude)),
                                    title = store.name,
                                    onClick = {
                                        viewModel.selectStore(store)
                                        true
                                    }
                                )
                            }
                        }
                    }
                    MapType.IMAGE_MAP -> {
                        ImageMapView(
                            query = uiState.searchQuery,
                            markers = uiState.imageMarkers,
                            onMarkerClick = { viewModel.selectStore(it) }
                        )
                    }
                }
            }

            // 2. Floating Search Bar
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                shadowElevation = 4.dp
            ) {
                TextField(
                    value = uiState.searchQuery,
                    onValueChange = { viewModel.onSearchQueryChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search city or area...") },
                    leadingIcon = { 
                        if (uiState.isSearching) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                        } else {
                            Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        }
                    },
                    trailingIcon = {
                        if (uiState.searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear")
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        viewModel.performSearch()
                        focusManager.clearFocus()
                    }),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    singleLine = true
                )
            }

            // 3. Search this area button
            if (uiState.mapType == MapType.GOOGLE_MAPS && cameraPositionState.isMoving) {
                Button(
                    onClick = { viewModel.searchThisArea(cameraPositionState.position.target) },
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Search this area")
                }
            }

            // 4. Store Details Bottom Sheet
            if (showBottomSheet && uiState.selectedStore != null) {
                ModalBottomSheet(
                    onDismissRequest = { 
                        showBottomSheet = false
                        viewModel.selectStore(null)
                    },
                    sheetState = sheetState,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    StoreDetailContent(uiState.selectedStore!!)
                }
            }
        }
    }
}

@Composable
fun ImageMapView(
    query: String,
    markers: List<ImageMarker>,
    onMarkerClick: (Store) -> Unit
) {
    val context = LocalContext.current
    val imageRes = remember(query) {
        val name = when {
            query.contains("Yelahanka", ignoreCase = true) -> "map_yelahanka"
            query.contains("Whitefield", ignoreCase = true) -> "map_whitefield"
            else -> "ic_launcher_background"
        }
        val id = context.resources.getIdentifier(name, "drawable", context.packageName)
        if (id != 0) id else android.R.drawable.ic_menu_report_image
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        var imageLoaded by remember { mutableStateOf(false) }
        val imageScale by animateFloatAsState(
            targetValue = if (imageLoaded) 1f else 1.1f,
            animationSpec = tween(1000),
            label = "imageScale"
        )
        
        LaunchedEffect(imageRes) {
            imageLoaded = false
            kotlinx.coroutines.delay(100)
            imageLoaded = true
        }

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Map View",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(scaleX = imageScale, scaleY = imageScale),
            contentScale = ContentScale.Crop
        )

        markers.forEachIndexed { index, marker ->
            var markerVisible by remember { mutableStateOf(false) }
            LaunchedEffect(imageRes) {
                kotlinx.coroutines.delay(300L + (index * 150L))
                markerVisible = true
            }

            Box(
                modifier = Modifier
                    .offset(
                        x = screenWidth * marker.offsetX - 24.dp,
                        y = screenHeight * marker.offsetY - 48.dp
                    )
            ) {
                AnimatedVisibility(
                    visible = markerVisible,
                    enter = scaleIn(tween(500)) + fadeIn(tween(500))
                ) {
                    MapMarkerIcon { onMarkerClick(marker.store) }
                }
            }
        }
    }
}

@Composable
fun MapMarkerIcon(onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 6.dp
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(4.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = CircleShape,
                    color = Color(0xFFE53935)
                ) {
                    Icon(
                        imageVector = Icons.Default.HealthAndSafety,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        }
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .offset(y = (-8).dp)
        )
    }
}

@Composable
fun StoreDetailContent(store: Store) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .padding(bottom = 32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = store.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AssistChip(
                        onClick = { },
                        label = { Text(if (store.isOpen) "Open Now" else "Closed") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Circle,
                                contentDescription = null,
                                modifier = Modifier.size(10.dp),
                                tint = if (store.isOpen) Color(0xFF4CAF50) else Color(0xFFF44336)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "• ${store.distance}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Place, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = store.address,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FilledTonalButton(
                onClick = { /* Call */ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Phone, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Call")
            }
            Button(
                onClick = { /* Directions */ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Directions, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Directions")
            }
        }
    }
}
