package com.example.composeresponsivelayoutgrid.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

enum class ScreenType {
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
) {

    private fun isValidColumnNo(columnNo: Int) = columnNo > 0 && columnNo <= this.noOfColumns

    private fun isValidGutterWidth(gutterWidth: Dp) =
        gutterWidth > 0.dp && gutterWidth < this.columnWidth

    fun widthFromRatio(weight: Float): Dp {
        val columns = (weight * noOfColumns).roundToInt()
        return (columns * columnWidth.value).dp + ((columns - 1) * gutterWidth.value).dp
    }

    fun widthBetweenColumns(start: Int, end: Int, gutterWidth: Dp = this.gutterWidth): Dp {
        if (!isValidColumnNo(start) || !isValidColumnNo(end)) {
            throw IllegalStateException("Invalid Column Number")
        }
        if (!isValidGutterWidth(gutterWidth)) {
            throw IllegalStateException("Gutter Width needs to be within the column width")
        }
        return endOffsetForColumn(end, gutterWidth) - startOffsetForColumn(start, gutterWidth)
    }

    fun startOffsetForColumn(columnNo: Int, gutterWidth: Dp = this.gutterWidth): Dp {
        if (!isValidColumnNo(columnNo)) {
            throw IllegalStateException("Invalid Column Number")
        }
        if (!isValidGutterWidth(gutterWidth)) {
            throw IllegalStateException("Gutter Width needs to be within the column width")
        }
        val bodyOffset = marginWidth
        // Readjust the column width based on the gutter width that is provided
        val columnWidth = this.columnWidth + (this.gutterWidth - gutterWidth)
        val startOffset = -1
        return bodyOffset + ((columnNo + startOffset) * columnWidth.value).dp + ((columnNo - 1) * gutterWidth.value).dp
    }

    fun endOffsetForColumn(columnNo: Int, gutterWidth: Dp = this.gutterWidth): Dp {
        if (!isValidColumnNo(columnNo)) {
            throw IllegalStateException("Invalid Column Number")
        }
        if (!isValidGutterWidth(gutterWidth)) {
            throw IllegalStateException("Gutter Width needs to be within the column width")
        }
        val bodyOffset = marginWidth
        // Readjust the column width based on the gutter width that is provided
        val columnWidth = this.columnWidth + (this.gutterWidth - gutterWidth)
        return bodyOffset + (columnNo * columnWidth.value).dp + ((columnNo - 1) * gutterWidth.value).dp
    }
}

fun screenTypesFactory(dimension: Dp) = when (dimension.value.roundToInt().dp) {
    in (0..599).map { it.dp } -> ScreenType.PHONE
    in (600..904).map { it.dp } -> ScreenType.TABLET_NORMAL
    in (905..1239).map { it.dp } -> ScreenType.TABLET_LARGE
    in (1240..1439).map { it.dp } -> ScreenType.LAPTOP
    else -> ScreenType.DESKTOP
}


fun getResponsiveDimensions(totalScreenWidth: Dp) = when (screenTypesFactory(totalScreenWidth)) {
    ScreenType.PHONE -> {
        val noOfColumns = 4
        val gutterWidth = 16.dp
        val marginWidth = 16.dp
        val columnWidth =
            ((totalScreenWidth - ((2 * marginWidth.value).dp) - (((noOfColumns - 1) * gutterWidth.value).dp)) / noOfColumns)
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenType.TABLET_NORMAL -> {
        val noOfColumns = 8
        val gutterWidth = 24.dp
        val marginWidth = 32.dp
        val columnWidth =
            ((totalScreenWidth - ((2 * marginWidth.value).dp) - (((noOfColumns - 1) * gutterWidth.value).dp)) / noOfColumns)
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenType.TABLET_LARGE -> {
        val noOfColumns = 12
        val gutterWidth = 24.dp
        val marginWidth = (totalScreenWidth - 840.dp) / 2
        val columnWidth = (840.dp - ((noOfColumns - 1) * gutterWidth.value).dp) / 12
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenType.LAPTOP -> {
        val noOfColumns = 12
        val gutterWidth = 32.dp
        val marginWidth = 200.dp
        val columnWidth =
            ((totalScreenWidth - ((2 * marginWidth.value).dp) - (((noOfColumns - 1) * gutterWidth.value).dp)) / noOfColumns)
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenType.DESKTOP -> {
        val noOfColumns = 12
        val gutterWidth = 32.dp
        val marginWidth = (totalScreenWidth - 1040.dp) / 2
        val columnWidth = (1040.dp - ((noOfColumns - 1) * gutterWidth.value).dp) / 12
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
}

