package com.example.agecalculator

import android.os.Build
import android.widget.Toast
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.core.i18n.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeCalculatorApp() {

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


    val daysLivedGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF3A0CA3), // Deep Indigo
            Color(0xFF7209B7), // Vibrant Violet
            Color(0xFFF72585)  // Pink accent
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 400f)
    )

    val hoursLivedGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF8500), // Orange
            Color(0xFFFF5C00), // Deep Orange-Red
            Color(0xFFFF006E)  // Pink-Red Fusion
        ),
        start = Offset(0f, 0f),
        end = Offset(800f, 600f)
    )

    val minutesLivedGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00B4D8), // Aqua Blue
            Color(0xFF0077B6), // Ocean Blue
            Color(0xFF023E8A)  // Deep Sea Blue
        ),
        start = Offset(0f, 0f),
        end = Offset(900f, 300f)
    )

    val heartbeatGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFD00000), // Strong Red
            Color(0xFF9D0208), // Deep Blood Red
            Color(0xFFBA181B)  // Warm Scarlet
        ),
        start = Offset(0f, 0f),
        end = Offset(800f, 800f)
    )

    val nextBirthdateGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFC300), // Bright Yellow
            Color(0xFFFF5733), // Warm Orange
            Color(0xFFC70039)  // Ruby Red
        ),
        start = Offset(0f, 0f),
        end = Offset(900f, 600f)
    )

    val secondsLivedGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF4361EE), // Bright Indigo
            Color(0xFF4895EF), // Sky Blue
            Color(0xFF4CC9F0)  // Soft Cyan
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 500f)
    )

    val helperFunction = HelperFunctions()


    var birthDateInput by remember { mutableStateOf("") }
    var currentDate by remember { mutableStateOf("") }
    var personAge by remember { mutableIntStateOf(0) }
    var personMonths by remember { mutableStateOf(0) }
    var personDays by remember { mutableStateOf(0) }
    var personHours by remember { mutableStateOf(0) }

    var totalYearsLived: Long by remember { mutableStateOf(8) }
    var totalDaysLived: Long by remember { mutableStateOf(0) }
    var totalHoursLived: Long by remember { mutableStateOf(0) }
    var totalMinutesLived: Long by remember { mutableStateOf(0) }
    var totalSecondsLived: Long by remember { mutableStateOf(0) }
    var totalHeartBeats: Long by remember { mutableStateOf(0) }
    var nextBirthdayIn: Long by remember { mutableStateOf(0) }

    var showDOBPickerDialog by remember { mutableStateOf(false) }
    var showCurrentDatePickerDialog by remember { mutableStateOf(false) }

    var dOBInMillis by remember { mutableLongStateOf(0) }
    var chosenDateInMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }
    val context = LocalContext.current


    if (showDOBPickerDialog) {
        SelectDate(
            onDismiss = { showDOBPickerDialog = false },
            onDateSelected = { millis, selectedDate ->
                if (helperFunction.isValidDate(millis, chosenDateInMillis)){
                    dOBInMillis = millis
                    birthDateInput = selectedDate
                    showDOBPickerDialog =false
                }else{
                    Toast.makeText(context,"Birth date cannot be in the future. Please select a past date.", Toast.LENGTH_LONG).show()
                }

            }
        )
    }

    if (showCurrentDatePickerDialog) {
        SelectDate(
            onDismiss = { showCurrentDatePickerDialog = false },
            onDateSelected = { millis, selectedDate ->
                if (helperFunction.isValidDate(dOBInMillis, millis)){
                    chosenDateInMillis = millis
                    currentDate = selectedDate
                    showCurrentDatePickerDialog =false
                }else{
                    Toast.makeText(context,"Current date must be after the birth date.", Toast.LENGTH_LONG).show()
                }
            }
        )
    }


    LaunchedEffect(Unit) {
        val formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
        currentDate = java.time.LocalDate.now().format(formatter).toString()

    }

    LaunchedEffect(currentDate, birthDateInput) {
        if (dOBInMillis > 0L) {
            val (years, months, days) = helperFunction.computeAgeParts(dOBInMillis, chosenDateInMillis)
            personAge = years
            personMonths = months
            personDays = days
            personHours = java.time.LocalTime.now().hour


            val totals = helperFunction.computeTotalsSince(dOBInMillis, chosenDateInMillis)
            totalHeartBeats = totals.days * 75 * 60 * 24
            totalDaysLived = totals.days
            totalHoursLived = totals.hours
            totalMinutesLived = totals.minutes
            totalSecondsLived = totals.seconds

        nextBirthdayIn = helperFunction.daysUntilNextBirthday(dOBInMillis, chosenDateInMillis)
        }    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Text(
                                "Age Matrix",
                                style = MaterialTheme.typography.titleLarge,
                            )
                            Spacer(Modifier.height(4.dp))
                            Text("Measure your life in moments",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.W700,
                                fontFamily = FontFamily.Monospace)
                        }
                },
                scrollBehavior = scrollBehavior)
        }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(brush = gradientBrush)
        ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            item() {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
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
                            Spacer(Modifier.height(4.dp))

                            Box() {
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

                }
            }

            item {
                Box(
                    Modifier
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
                        if (birthDateInput.isNotBlank()) {
                            Text(
                                text = "$personAge",
                                style = MaterialTheme.typography.displayLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "years old",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                            )
                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = "$personMonths months • $personDays days • $personHours hours",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )

                        } else {
                            Text(
                                "--",
                                fontSize = 70.sp,
                                fontWeight = FontWeight.W700,
                                color = Color.White.copy(alpha = 0.3f)
                            )
                            Text(
                                "Awaiting birth date",
                                fontSize = 18.sp,
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        }
                    }



                }
            }

            if (birthDateInput.isNotBlank()){
                item {
                    ColourfulCards(
                        icon = Icons.Default.CalendarToday,
                        brush = daysLivedGradient,
                        title = "Total Days Lived",
                        count = totalDaysLived
                    )
                    ColourfulCards(
                        icon = Icons.Default.AccessTime,
                        brush = hoursLivedGradient,
                        title = "Total Hours Lived",
                        count = totalHoursLived
                    )
                    ColourfulCards(
                        icon = Icons.Default.AutoAwesome,
                        brush = minutesLivedGradient,
                        title = "Total Minutes Lived",
                        count = totalMinutesLived
                    )
                    ColourfulCards(
                        icon = Icons.Default.FavoriteBorder,
                        brush = heartbeatGradient,
                        title = "Total Heartbeat",
                        count = totalHeartBeats
                    )
                    ColourfulCards(
                        icon = Icons.Default.Cake,
                        brush = nextBirthdateGradient,
                        title = "Next Birthdate",
                        count = nextBirthdayIn,
                        description = "days to celebrate!"
                    )
                    ColourfulCards(
                        icon = Icons.Default.Timeline,
                        brush = secondsLivedGradient,
                        title = "You've been alive for",
                        count = totalSecondsLived,
                        description = "seconds and counting..."
                    )

                }
            }
        }
    }

    }
}



@Composable
fun ColourfulCards(
    icon: ImageVector,
    brush: Brush,
    title: String,
    count: Long,
    description: String? = null

) {
    Box(
        Modifier
            .padding(top = 12.dp)
            .background(brush, shape = RoundedCornerShape(16.dp), alpha = .7f)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(icon, contentDescription = "icon")
                Spacer(Modifier.width(8.dp))
                Text(
                    title,
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                "$count",
                fontSize = 30.sp,
                fontWeight = FontWeight.W700
            )
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
    onDateSelected: (Long, String) -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton({
                datePickerState.selectedDateMillis?.let { millis ->
                    val formatted =
                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(millis))
                    onDateSelected(millis,formatted)

                }
            }) {
                Text("Confirm")
            }

        },
        dismissButton = {
            TextButton({ onDismiss() }) {
                Text("Cancel")
            }
        },
    ) {
        DatePicker(
            state = datePickerState,
        )
    }
}