package com.example.agecalculator

import android.os.Build
import androidx.annotation.RequiresApi

class HelperFunctions {
    fun isValidDate(dOBInMillis: Long, chosenDateInMillis: Long): Boolean{
        return dOBInMillis < chosenDateInMillis
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun millisToLocalDate(millis: Long): java.time.LocalDate{
        return java.time.Instant.ofEpochMilli(millis)
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun computeAgeParts(dobMillis: Long,endDateMillis: Long = System.currentTimeMillis()): Triple<Int, Int, Int> {
        val dob = millisToLocalDate(dobMillis)
        val endDate = millisToLocalDate(endDateMillis)
        val period = java.time.Period.between(dob, endDate)
        return Triple(period.years, period.months, period.days)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    data class Totals(
        val days: Long,
        val hours: Long,
        val minutes: Long,
        val seconds: Long
    )

    @RequiresApi(Build.VERSION_CODES.S)
    fun computeTotalsSince(dobMillis: Long, endDateMillis: Long = System.currentTimeMillis()): Totals {
        val dobInstant = java.time.Instant.ofEpochMilli(dobMillis)
        val referenceInstant = java.time.Instant.ofEpochMilli(endDateMillis)
        val duration = java.time.Duration.between(dobInstant, referenceInstant)

        val seconds = duration.seconds
        val minutes = duration.toMinutes()
        val hours = duration.toHours()
        val days = duration.toDays()

        return Totals(days = days, hours = hours, minutes = minutes, seconds = seconds)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun daysUntilNextBirthday(dobMillis: Long, endDateMillis: Long = System.currentTimeMillis()): Long {
        val dob = millisToLocalDate(dobMillis)
        val today = millisToLocalDate(endDateMillis)

        if (dob.month == java.time.Month.FEBRUARY && dob.dayOfMonth == 29) {
            return daysUntilNextLeapYearBirthday(today)
        }

        var nextBirthday = java.time.LocalDate.of(today.year, dob.month, dob.dayOfMonth)
        if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
            nextBirthday = nextBirthday.plusYears(1)
        }
        return java.time.temporal.ChronoUnit.DAYS.between(today, nextBirthday)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun daysUntilNextLeapYearBirthday(today: java.time.LocalDate): Long {
        var year = today.year

        while (!java.time.Year.isLeap(year.toLong())) {
            year++
        }

        val nextLeapBirthday = java.time.LocalDate.of(year, java.time.Month.FEBRUARY, 29)

        if (nextLeapBirthday.isBefore(today) || nextLeapBirthday.isEqual(today)) {
            year += 4
            while (!java.time.Year.isLeap(year.toLong())) {
                year++
            }
            return java.time.temporal.ChronoUnit.DAYS.between(
                today,
                java.time.LocalDate.of(year, java.time.Month.FEBRUARY, 29)
            )
        }

        return java.time.temporal.ChronoUnit.DAYS.between(today, nextLeapBirthday)
    }


}