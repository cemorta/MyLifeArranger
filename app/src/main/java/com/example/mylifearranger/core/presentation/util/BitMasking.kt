package com.example.mylifearranger.core.presentation.util

fun returnDayStringByBitMasking(bitMask: Int): String {
    var dayString = ""
    if (bitMask and 1 == 1) {
        dayString += "Mo"
    }
    if (bitMask and 2 == 2) {
        dayString += "Tu"
    }
    if (bitMask and 4 == 4) {
        dayString += "We"
    }
    if (bitMask and 8 == 8) {
        dayString += "Th"
    }
    if (bitMask and 16 == 16) {
        dayString += "Fr"
    }
    if (bitMask and 32 == 32) {
        dayString += "Sa"
    }
    if (bitMask and 64 == 64) {
        dayString += "Su"
    }
    return dayString
}