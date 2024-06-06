package com.kisal.viedeoeditor.misc

fun validateUInt(string: String): String {
    if (string.toUIntOrNull() == null) {
        return "Input must be a valid unsigned integer"
    }
    return ""
}

fun validateUIntAndNonzero(string: String): String {
    val uint = string.toUIntOrNull()
    if (uint == null) {
        return "Input must be a valid unsigned integer"
    } else if (uint == 0U) {
        return "Input must be nonzero"
    }
    return ""
}

