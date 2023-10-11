package com.example.mylifearranger.core.presentation.util

fun returnDayStringByBitMasking(bitMask: Int, daysOfWeek: Array<String>): String {
    var dayString = ""
    if (bitMask and 1 == 1) {
        dayString = dayString + daysOfWeek[0] + " "
    }
    if (bitMask and 2 == 2) {
        dayString = dayString + daysOfWeek[1] + " "
    }
    if (bitMask and 4 == 4) {
        dayString = dayString + daysOfWeek[2] + " "
    }
    if (bitMask and 8 == 8) {
        dayString = dayString + daysOfWeek[3] + " "
    }
    if (bitMask and 16 == 16) {
        dayString = dayString + daysOfWeek[4] + " "
    }
    if (bitMask and 32 == 32) {
        dayString = dayString + daysOfWeek[5] + " "
    }
    if (bitMask and 64 == 64) {
        dayString = dayString + daysOfWeek[6] + " "
    }
    return dayString
}