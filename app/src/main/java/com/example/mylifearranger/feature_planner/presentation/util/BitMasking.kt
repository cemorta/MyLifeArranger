package com.example.mylifearranger.feature_planner.presentation.util

fun returnDayStringByBitMasking(bitMask: Int): String {
    var dayString = ""
    if (bitMask and 1 == 1) {
        dayString += "M"
    }
    if (bitMask and 2 == 2) {
        dayString += "T"
    }
    if (bitMask and 4 == 4) {
        dayString += "W"
    }
    if (bitMask and 8 == 8) {
        dayString += "T"
    }
    if (bitMask and 16 == 16) {
        dayString += "F"
    }
    if (bitMask and 32 == 32) {
        dayString += "S"
    }
    if (bitMask and 64 == 64) {
        dayString += "S"
    }
    return dayString
}