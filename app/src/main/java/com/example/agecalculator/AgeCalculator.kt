package com.example.agecalculator

import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeCalculatorApp(){

    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xff352c81), Color(0xff591c86)),
        startX = 0.0f,
        endX = 500.0f
    )

    val birthdayCardBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xffea4798), Color(0xff9433ea)),
        startX = 100.0f,
        endX = 800.0f
    )


    var birthDateInput by remember { mutableStateOf("") }
    var currentDate by remember { mutableStateOf("") }
    var personAge by remember { mutableStateOf("26") }
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
                        Card(Modifier.fillMaxSize(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent.copy(.3f)
                            )) {
                            Column(Modifier.padding(16.dp)) {
                                Text("\uD83D\uDDD3\uFE0F Enter Your Birth Date")

                                Spacer(Modifier.height(4.dp))

                                OutlinedTextField(
                                    value = birthDateInput,
                                    onValueChange = { birthDateInput = it},
                                    label = { Text("Enter your birthdate")},
                                    modifier = Modifier.fillMaxWidth(),
                                    trailingIcon = {
                                        Icon(Icons.Default.DateRange,contentDescription = "bDate")
                                    }

                                )
                                Spacer(Modifier.height(8.dp))

                                Text("\uD83D\uDDD3\uFE0F Current Date")


                                OutlinedTextField(
                                    value = currentDate,
                                    onValueChange = { currentDate = it},
                                    label = { Text("Enter Current Date")},
                                    modifier = Modifier.fillMaxWidth(),
                                    trailingIcon = {
                                        Icon(Icons.Default.DateRange,contentDescription = "cDate")
                                    }

                                )

                                
                                
                            }
                        }
                        Spacer(Modifier.height(16.dp))

                        Box(Modifier
                            .fillMaxSize()
                            .background(brush = birthdayCardBrush, shape = RoundedCornerShape(16.dp), alpha = .7f)
                        ) {
                            Column(
                                Modifier.fillMaxWidth().padding(vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text("You are",
                                    fontSize = 18.sp)
                                Text(personAge, fontSize = 70.sp, fontWeight = FontWeight.W700)
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
                    ColourfulCards(icon = Icons.Default.CalendarToday, brush = birthdayCardBrush, title = "Total Days Lived", count = totalYearsLived)
                    ColourfulCards(icon = Icons.Default.AccessTime, brush = birthdayCardBrush, title = "Total Days Lived", count = totalYearsLived)
                    ColourfulCards(icon = Icons.Default.AutoAwesome, brush = birthdayCardBrush, title = "Total Days Lived", count = totalYearsLived)
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
    count: Int

){
    Box(Modifier.padding(vertical = 16.dp).background(brush, shape = RoundedCornerShape(16.dp), alpha = .7f)){
        Column(
            Modifier.fillMaxWidth().padding(start = 24.dp, top = 16.dp, bottom = 16.dp),
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
        }
    }
}