package com.example.composeresponsivelayoutgrid

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composeresponsivelayoutgrid.core.ResponsiveDimensions
import com.example.composeresponsivelayoutgrid.core.getResponsiveDimensions

@Composable
fun ResponsiveBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.(ResponsiveDimensions) -> Unit
) {
    BoxWithConstraints(
        modifier.fillMaxSize(),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints
    ) {
        content(getResponsiveDimensions(this.maxWidth))
    }
}