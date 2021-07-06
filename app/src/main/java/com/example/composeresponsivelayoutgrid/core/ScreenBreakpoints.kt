package com.example.composeresponsivelayoutgrid.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

enum class ScreenTypes {
    PHONE,
    TABLET_NORMAL,
    TABLET_LARGE,
    LAPTOP,
    DESKTOP
}

data class ResponsiveDimensions(
    val noOfColumns: Int,
    val columnWidth: Dp,
    val gutterWidth: Dp,
    val marginWidth: Dp
)

fun screenTypesFactory(dimension: Dp) = when (dimension.value.roundToInt().dp) {
    in (0..599).map { it.dp } -> ScreenTypes.PHONE
    in (600..904).map { it.dp } -> ScreenTypes.TABLET_NORMAL
    in (905..1239).map { it.dp } -> ScreenTypes.TABLET_LARGE
    in (1240..1439).map { it.dp } -> ScreenTypes.LAPTOP
    else -> ScreenTypes.DESKTOP
}


fun getResponsiveDimensions(totalScreenWidth: Dp) = when (screenTypesFactory(totalScreenWidth)) {
    ScreenTypes.PHONE -> {
        val noOfColumns = 4
        val gutterWidth = 16.dp
        val marginWidth = 16.dp
        val columnWidth =
            ((totalScreenWidth - ((2 * marginWidth.value).dp) - (((noOfColumns - 1) * gutterWidth.value).dp)) / noOfColumns)
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenTypes.TABLET_NORMAL -> {
        val noOfColumns = 8
        val gutterWidth = 24.dp
        val marginWidth = 32.dp
        val columnWidth =
            ((totalScreenWidth - ((2 * marginWidth.value).dp) - (((noOfColumns - 1) * gutterWidth.value).dp)) / noOfColumns)
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenTypes.TABLET_LARGE -> {
        val noOfColumns = 12
        val gutterWidth = 24.dp
        val marginWidth = (totalScreenWidth - 840.dp - (((noOfColumns - 1) * gutterWidth.value).dp))
        val columnWidth = 840.dp / 12
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenTypes.LAPTOP -> {
        val noOfColumns = 12
        val gutterWidth = 32.dp
        val marginWidth = 200.dp
        val columnWidth =
            ((totalScreenWidth - ((2 * marginWidth.value).dp) - (((noOfColumns - 1) * gutterWidth.value).dp)) / noOfColumns)
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenTypes.DESKTOP -> {
        val noOfColumns = 12
        val gutterWidth = 32.dp
        val marginWidth =
            (totalScreenWidth - 1040.dp - (((noOfColumns - 1) * gutterWidth.value).dp))
        val columnWidth = 1040.dp / 12
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
}

