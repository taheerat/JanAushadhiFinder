package com.janaushadhi.finder.ui.profile

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.janaushadhi.finder.data.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.showEditDialog) {
        EditProfileDialog(
            user = uiState.user,
            onDismiss = { viewModel.setShowEditDialog(false) },
            onConfirm = { name, phone -> viewModel.updateProfile(name, phone) }
        )
    }

    if (uiState.showHelpDialog) {
        HelpDialog(onDismiss = { viewModel.setShowHelpDialog(false) })
    }

    if (uiState.showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.setShowLogoutDialog(false) },
            title = { Text("Logout") },
            text = { Text("Are you sure you want to logout from Jan Aushadhi Finder?") },
            confirmButton = {
                TextButton(onClick = { viewModel.logout(onLogout) }) {
                    Text("Logout", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.setShowLogoutDialog(false) }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text("Profile", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 1. User Header
            item {
                UserHeader(uiState.user)
            }

            // Quick Action Buttons
            item {
                ProfileQuickActions(
                    onEditClick = { viewModel.setShowEditDialog(true) },
                    onHelpClick = { viewModel.setShowHelpDialog(true) }
                )
            }

            // 2. Healthcare History
            item {
                SectionHeader(title = "Medicine & Activity History", icon = Icons.Default.History)
            }
            items(uiState.history) { item ->
                HistoryListItem(item)
            }

            // 3. Prescriptions
            item {
                SectionHeader(title = "Uploaded Prescriptions", icon = Icons.Default.Description)
            }
            if (uiState.prescriptions.isEmpty()) {
                item {
                    Text(
                        "No prescriptions uploaded yet.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            } else {
                items(uiState.prescriptions) { prescription ->
                    PrescriptionListItem(
                        prescription = prescription,
                        onDelete = { viewModel.deletePrescription(prescription.id) }
                    )
                }
            }

            // 4. Settings
            item {
                SectionHeader(title = "Settings", icon = Icons.Default.Settings)
            }
            item {
                SettingsSection(
                    state = uiState.settings,
                    onDarkModeToggle = viewModel::toggleDarkMode,
                    onNotificationsToggle = viewModel::toggleNotifications,
                    onRemindersToggle = viewModel::toggleReminders
                )
            }

            // 5. Logout
            item {
                OutlinedButton(
                    onClick = { viewModel.setShowLogoutDialog(true) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.error))
                ) {
                    Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout")
                }
            }
        }
    }
}

@Composable
fun UserHeader(user: UserProfile?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = user?.name?.firstOrNull()?.toString() ?: "U",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = user?.name ?: "User Name",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = user?.phone ?: "+91 XXXXX XXXXX",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
                Text(
                    text = user?.email ?: "user@email.com",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun ProfileQuickActions(
    onEditClick: () -> Unit,
    onHelpClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        QuickActionButton(Icons.Default.Edit, "Edit", Modifier.weight(1f), onClick = onEditClick)
        QuickActionButton(Icons.Default.Bookmark, "Saved", Modifier.weight(1f))
        QuickActionButton(Icons.Default.Download, "Reports", Modifier.weight(1f))
        QuickActionButton(Icons.AutoMirrored.Filled.Help, "Help", Modifier.weight(1f), onClick = onHelpClick)
    }
}

@Composable
fun QuickActionButton(
    icon: ImageVector, 
    label: String, 
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(label, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun SectionHeader(title: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun HistoryListItem(item: SearchHistoryItem) {
    val icon = when (item.type) {
        HistoryType.MEDICINE -> Icons.Default.Medication
        HistoryType.AI_QUERY -> Icons.Default.SmartToy
        HistoryType.STORE -> Icons.Default.Store
    }
    ListItem(
        headlineContent = { Text(item.query, fontWeight = FontWeight.Medium) },
        supportingContent = { Text(item.date) },
        leadingContent = { 
            Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.secondary) 
        },
        trailingContent = { 
            Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, modifier = Modifier.size(16.dp)) 
        },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent)
    )
}

@Composable
fun PrescriptionListItem(prescription: PrescriptionItem, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.PictureAsPdf,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(prescription.fileName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                Text("Doctor: ${prescription.doctorName}", style = MaterialTheme.typography.bodySmall)
                Text("${prescription.date} • ${prescription.medicineCount} Medicines", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Row {
                IconButton(onClick = { /* View */ }) {
                    Icon(Icons.Default.Visibility, contentDescription = "View", modifier = Modifier.size(20.dp))
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
fun SettingsSection(
    state: SettingsState,
    onDarkModeToggle: (Boolean) -> Unit,
    onNotificationsToggle: (Boolean) -> Unit,
    onRemindersToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
    ) {
        SettingsItem(
            icon = Icons.Default.DarkMode,
            title = "Dark Mode",
            trailingContent = {
                Switch(checked = state.isDarkMode, onCheckedChange = onDarkModeToggle)
            }
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
        SettingsItem(
            icon = Icons.Default.Notifications,
            title = "Notifications",
            trailingContent = {
                Switch(checked = state.notificationsEnabled, onCheckedChange = onNotificationsToggle)
            }
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
        SettingsItem(
            icon = Icons.Default.Alarm,
            title = "Medicine Reminders",
            trailingContent = {
                Switch(checked = state.medicineRemindersEnabled, onCheckedChange = onRemindersToggle)
            }
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
        SettingsItem(
            icon = Icons.Default.Language,
            title = "Language",
            trailingContent = {
                Text(state.language, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
            }
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
        SettingsItem(icon = Icons.Default.PrivacyTip, title = "Privacy Policy")
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
        SettingsItem(icon = Icons.Default.Info, title = "About App")
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    trailingContent: @Composable (() -> Unit)? = null
) {
    ListItem(
        leadingContent = { Icon(icon, contentDescription = null, modifier = Modifier.size(22.dp)) },
        headlineContent = { Text(title, style = MaterialTheme.typography.bodyLarge) },
        trailingContent = trailingContent,
        colors = ListItemDefaults.colors(containerColor = Color.Transparent)
    )
}

@Composable
fun EditProfileDialog(
    user: UserProfile?,
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var name by remember { mutableStateOf(user?.name ?: "") }
    var phone by remember { mutableStateOf(user?.phone ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Profile") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, phone) }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun HelpDialog(onDismiss: () -> Unit) {
    val faqs = listOf(
        "1. What are generic medicines?" to "Generic medicines are medicines that contain the same active ingredients as branded medicines but are sold at lower prices.",
        "2. Are generic medicines safe?" to "Yes, approved generic medicines are tested for safety and quality before being sold.",
        "3. Why are generic medicines cheaper?" to "They are cheaper because companies do not spend heavily on branding, advertising, or marketing.",
        "4. Do generic medicines work the same as branded medicines?" to "Yes, they are meant to provide the same medical effect as branded medicines.",
        "5. Can doctors prescribe generic medicines?" to "Yes, doctors in India can prescribe generic medicines.",
        "6. Where can I buy generic medicines in India?" to "They are available at pharmacies and government-supported stores like Pradhan Mantri Bhartiya Janaushadhi Pariyojana outlets.",
        "7. Do generic medicines have side effects?" to "They can have side effects similar to branded medicines because the active ingredient is the same.",
        "8. How can I identify a generic medicine?" to "Check the medicine composition or salt name written on the packaging.",
        "9. Are generic medicines approved by the government?" to "Yes, they must be approved by authorities such as the Central Drugs Standard Control Organization.",
        "10. Can I switch from branded to generic medicine on my own?" to "It is better to consult a doctor or pharmacist before switching medicines.",
        "11. Are generic medicines available for all diseases?" to "Many common medicines are available in generic form, but not every medicine has a generic version.",
        "12. Do generic medicines look different from branded ones?" to "Yes, the color, shape, or packaging may differ even if the medicine works the same.",
        "13. Is Jan Aushadhi medicine generic medicine?" to "Yes, medicines sold under Pradhan Mantri Bhartiya Janaushadhi Pariyojana are generic medicines."
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Generic Medicine FAQ") },
        text = {
            LazyColumn(
                modifier = Modifier.heightIn(max = 400.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(faqs) { (q, a) ->
                    Column {
                        Text(q, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(a, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(thickness = 0.5.dp)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}
