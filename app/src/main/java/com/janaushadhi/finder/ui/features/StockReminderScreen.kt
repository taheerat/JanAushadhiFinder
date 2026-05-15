package com.janaushadhi.finder.ui.features

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.janaushadhi.finder.data.model.MedicineReminder
import com.janaushadhi.finder.data.model.StockRequest
import com.janaushadhi.finder.data.model.StockStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockReminderScreen(
    viewModel: StockReminderViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Check Stock", "Refill Reminders")

    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("Care Tools", fontWeight = FontWeight.Bold) })
                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            when (selectedTab) {
                0 -> StockRequestTab(uiState.stockRequests, viewModel::requestStock, uiState.isSubmitting)
                1 -> RemindersTab(uiState.reminders, viewModel::addReminder, viewModel::deleteReminder)
            }
        }
        
        uiState.successMessage?.let { msg ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Snackbar(modifier = Modifier.padding(16.dp)) { Text(msg) }
            }
        }
    }
}

@Composable
fun StockRequestTab(
    requests: List<StockRequest>,
    onRequest: (String, String) -> Unit,
    isLoading: Boolean
) {
    var medName by remember { mutableStateOf("") }
    var storeName by remember { mutableStateOf("") }

    Column {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Inquire Medicine Availability", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = medName,
                    onValueChange = { medName = it },
                    label = { Text("Medicine Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = storeName,
                    onValueChange = { storeName = it },
                    label = { Text("Store Name/Location") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { 
                        if (medName.isNotBlank() && storeName.isNotBlank()) {
                            onRequest(medName, storeName)
                            medName = ""
                            storeName = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                ) {
                    if (isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                    else Text("Send Inquiry")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Recent Inquiries", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(requests) { request ->
                ListItem(
                    headlineContent = { Text(request.medicineName, fontWeight = FontWeight.SemiBold) },
                    supportingContent = { Text(request.storeName) },
                    trailingContent = {
                        Badge(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
                            Text(request.status.name)
                        }
                    },
                    colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                )
            }
        }
    }
}

@Composable
fun RemindersTab(
    reminders: List<MedicineReminder>,
    onAdd: (String, String, String) -> Unit,
    onDelete: (String) -> Unit
) {
    var medName by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Add Refill Reminder") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = medName, onValueChange = { medName = it }, label = { Text("Medicine") })
                    OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Refill Date (e.g. 25 Oct)") })
                    OutlinedTextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Quantity") })
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (medName.isNotBlank()) {
                        onAdd(medName, date, quantity)
                        showAddDialog = false
                        medName = ""; date = ""; quantity = ""
                    }
                }) { Text("Add") }
            },
            dismissButton = { TextButton(onClick = { showAddDialog = false }) { Text("Cancel") } }
        )
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Active Refill Trackers", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            IconButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.AddCircle, contentDescription = "Add", tint = MaterialTheme.colorScheme.primary)
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxSize()) {
            items(reminders) { reminder ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.NotificationImportant, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(reminder.medicineName, fontWeight = FontWeight.Bold)
                            Text("Next Refill: ${reminder.refillDate} • ${reminder.quantity}", style = MaterialTheme.typography.bodySmall)
                        }
                        IconButton(onClick = { onDelete(reminder.id) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red.copy(alpha = 0.7f))
                        }
                    }
                }
            }
        }
    }
}
