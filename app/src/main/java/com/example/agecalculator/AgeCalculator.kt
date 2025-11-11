package com.example.agecalculator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeCalculatorApp(){

    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF240046), // Deep violet
            Color(0xFF5A189A), // Rich purple
            Color(0xFF3C096C)  // Dark indigo
        ),
        start = Offset(0f, 0f),
        end = Offset(800f, 800f)
    )

    val birthdayCardBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFB5179E), // Magenta
            Color(0xFF7209B7), // Vivid violet
            Color(0xFF3A0CA3)  // Deep purple
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 500f)
    )

    // 🌈 Life Tracker – Unified Gradient Theme for ColourfulCards

// 1️⃣ Total Days Lived → Dawn Horizon (hopeful, bright start)
    val daysLivedGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF3A0CA3), // Deep Indigo
            Color(0xFF7209B7), // Vibrant Violet
            Color(0xFFF72585)  // Pink accent
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 400f)
    )

// 2️⃣ Total Hours Lived → Solar Energy (power, productivity)
    val hoursLivedGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF8500), // Orange
            Color(0xFFFF5C00), // Deep Orange-Red
            Color(0xFFFF006E)  // Pink-Red Fusion
        ),
        start = Offset(0f, 0f),
        end = Offset(800f, 600f)
    )

// 3️⃣ Total Minutes Lived → Aqua Pulse (flow of moments)
    val minutesLivedGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00B4D8), // Aqua Blue
            Color(0xFF0077B6), // Ocean Blue
            Color(0xFF023E8A)  // Deep Sea Blue
        ),
        start = Offset(0f, 0f),
        end = Offset(900f, 300f)
    )

// 4️⃣ Total Heartbeat → Crimson Pulse (life & vitality)
    val heartbeatGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFD00000), // Strong Red
            Color(0xFF9D0208), // Deep Blood Red
            Color(0xFFBA181B)  // Warm Scarlet
        ),
        start = Offset(0f, 0f),
        end = Offset(800f, 800f)
    )

// 5️⃣ Next Birthdate → Celebration Glow (festive, happy)
    val nextBirthdateGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFC300), // Bright Yellow
            Color(0xFFFF5733), // Warm Orange
            Color(0xFFC70039)  // Ruby Red
        ),
        start = Offset(0f, 0f),
        end = Offset(900f, 600f)
    )

// 6️⃣ Total Seconds Lived → Cosmic Energy (limitless time)
    val secondsLivedGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF4361EE), // Bright Indigo
            Color(0xFF4895EF), // Sky Blue
            Color(0xFF4CC9F0)  // Soft Cyan
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 500f)
    )



    var birthDateInput by remember { mutableStateOf("") }
    var currentDate by remember { mutableStateOf("") }
    var personAge by remember { mutableStateOf(0) }
    var personMonths by remember { mutableStateOf("8") }
    var personDays by remember { mutableStateOf("29") }
    var personHours by remember { mutableStateOf("9") }
    
    var totalYearsLived by remember { mutableIntStateOf(8) }
    var totalDaysLived by remember { mutableStateOf("") }
    var totalHoursLived by remember { mutableStateOf("") }
    var totalMinutesLived by remember { mutableStateOf("") }
    var totalSecondsLived by remember { mutableStateOf("") }
    var totalHeartBeats by remember { mutableStateOf("") }
    var nextBirthdayIn by remember { mutableStateOf("") }

    var showDOBPickerDialog by remember { mutableStateOf(false) }
    var showCurrentDatePickerDialog by remember { mutableStateOf(false) }

    var dOBInMillis by remember { mutableLongStateOf(0) }
    var choosenDateInMillis by remember { mutableLongStateOf(0) }

    if (showDOBPickerDialog){
        SelectDate(
            onDismiss = { showDOBPickerDialog = false },
            onConfirm = { selectedDate ->
                birthDateInput = selectedDate
                showDOBPickerDialog = false
            },
            selectedDateMillis = { millis ->
                dOBInMillis = millis
            }
        )
    }

    if (showCurrentDatePickerDialog){
        SelectDate(
            onDismiss = { showCurrentDatePickerDialog = false },
            onConfirm = { selectedDate ->
                currentDate = selectedDate
                showCurrentDatePickerDialog = false
            },
            selectedDateMillis = { millis ->
                choosenDateInMillis = millis
            }
        )
    }



    fun isValidDate(): Boolean{
        if (dOBInMillis < choosenDateInMillis ) {
            return true
        } else {
            return false
        }

    }

    fun millisToLocalDate(millis: Long): java.time.LocalDate{
        return java.time.Instant.ofEpochMilli(millis)
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate()
    }

    fun CalculateAge() {
        if (birthDateInput.isNotEmpty() && currentDate.isNotEmpty()) {
            if (isValidDate()) {
                personAge = (currentDate.split("/")[2].toInt()) - (birthDateInput.split("/")[2].toInt())



            }

        }
    }


    LaunchedEffect(Unit) {
        val todayDate  = System.currentTimeMillis()
        currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(todayDate)
    }

    LaunchedEffect(currentDate, birthDateInput) {
        CalculateAge()
    }


    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Age Calculator")
                }
            )
        }) { paddingValues ->

        Box(Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(brush = gradientBrush)){
            LazyColumn (modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)){
                item(){
                    Column(Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,) {
                        Text("✨ Time Traveler ✨",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.W700,
                            fontFamily = FontFamily.Monospace
                            )
                        Spacer(Modifier.height(4.dp))
                        Text("Discover your Journey through time \uD83E\uDD29")
                        Spacer(Modifier.height(16.dp))
                        Card(
                            Modifier
                                .fillMaxSize(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent.copy(alpha = 0.3f)
                            )
                        ) {
                                Column(Modifier.padding(16.dp)) {
                                    Text("🗓️ Enter Your Birth Date")
                                    Spacer(Modifier.height(4.dp))

                                    Box {
                                        OutlinedTextField(
                                            value = birthDateInput,
                                            onValueChange = { },
                                            placeholder = { Text("dd/mm/yyyy") },
                                            modifier = Modifier.fillMaxWidth(),
                                            readOnly = true,
                                            trailingIcon = {
                                                Icon(Icons.Default.DateRange, contentDescription = "bDate")
                                            },
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        Box(
                                            modifier = Modifier
                                                .matchParentSize()
                                                .clickable { showDOBPickerDialog = true }
                                        )
                                    }
                                    Spacer(Modifier.height(8.dp))

                                    Text("🗓️ Current Date")

                                    Box(){
                                        OutlinedTextField(
                                            value = currentDate,
                                            onValueChange = { },
                                            placeholder = { Text("dd/mm/yyyy") },
                                            modifier = Modifier.fillMaxWidth(),
                                            trailingIcon = {
                                                Icon(Icons.Default.DateRange, contentDescription = "cDate")
                                            },
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        Box(
                                            modifier = Modifier
                                                .matchParentSize()
                                                .clickable(onClick = {
                                                    showCurrentDatePickerDialog = true
                                                })
                                        )
                                    }
                                }
                        }

                        Spacer(Modifier.height(16.dp))

                        Box(Modifier
                            .fillMaxSize()
                            .background(
                                brush = birthdayCardBrush,
                                shape = RoundedCornerShape(16.dp),
                                alpha = .7f
                            )
                        ) {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Text("$personAge", fontSize = 70.sp, fontWeight = FontWeight.W700)
                                Text("years old",
                                    fontSize = 22.sp)
                                Spacer(Modifier.height(8.dp))
                                Text("$personMonths months, $personDays days, $personHours hours",
                                    fontSize = 18.sp)

                            }


                        }

                    }
                }

                item {
                    ColourfulCards(icon = Icons.Default.CalendarToday, brush = daysLivedGradient, title = "Total Days Lived", count = totalYearsLived)
                    ColourfulCards(icon = Icons.Default.AccessTime, brush = hoursLivedGradient, title = "Total Hours Lived", count = totalYearsLived)
                    ColourfulCards(icon = Icons.Default.AutoAwesome, brush = minutesLivedGradient, title = "Total Minutes Lived", count = totalYearsLived)
                    ColourfulCards(icon = Icons.Default.FavoriteBorder, brush = heartbeatGradient, title = "Total Heartbeat", count = totalYearsLived)
                    ColourfulCards(icon = Icons.Default.Cake, brush = nextBirthdateGradient, title = "Next Birthdate", count = totalYearsLived, description = "days to celebrate!")
                    ColourfulCards(icon = Icons.Default.Timeline, brush = secondsLivedGradient, title = "You've been alive for", count = totalYearsLived, description = "and counting...")

                }
            }
        }

    }
}



@Composable
fun ColourfulCards(
    icon: ImageVector,
    brush: Brush ,
    title: String,
    count: Int,
    description: String? = null

){
    Box(Modifier
        .padding(top = 12.dp)
        .background(brush, shape = RoundedCornerShape(16.dp), alpha = .7f)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                Icon(icon, contentDescription = "icon")
                Spacer(Modifier.width(8.dp))
                Text(title,
                    )
            }
            Spacer(Modifier.height(8.dp))
            Text("$count",
                fontSize = 30.sp,
                fontWeight = FontWeight.W700)
            if (description != null) {
                Text(description)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDate(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    selectedDateMillis: (Long) -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton({
                datePickerState.selectedDateMillis?.let { millis ->
                    selectedDateMillis(millis)
                    val formatted =
                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(millis))
                    onConfirm(formatted)

                }
            }) {
                Text("Confirm")
            }

        },
        dismissButton = {
            TextButton({ onDismiss() }) {
                Text("Cancel")
            }
        }) {
        DatePicker(datePickerState)

    }
}