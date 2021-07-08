package com.example.composeresponsivelayoutgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.composeresponsivelayoutgrid.ui.theme.ComposeResponsiveLayoutGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeResponsiveLayoutGridTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ResponsiveBox() { dimensions, _ ->
                        Row(Modifier.fillMaxSize()) {
                            // Left Margin
                            Spacer(
                                Modifier
                                    .fillMaxHeight()
                                    .width(dimensions.marginWidth)
                                    .background(MaterialTheme.colors.secondary)
                            )
                            (1..(dimensions.noOfColumns)).forEach {
                                // Columns
                                Column(
                                    Modifier
                                        .fillMaxHeight()
                                        .width(dimensions.columnWidth)
                                        .background(MaterialTheme.colors.primary)
                                ) {}
                                // Gutters
                                if (it != dimensions.noOfColumns) {
                                    Spacer(
                                        Modifier
                                            .fillMaxHeight()
                                            .width(dimensions.gutterWidth)
                                            .background(MaterialTheme.colors.onPrimary)
                                    )
                                }
                            }
                            // Right Margin
                            Spacer(
                                Modifier
                                    .fillMaxHeight()
                                    .width(dimensions.marginWidth)
                                    .background(MaterialTheme.colors.secondary)
                            )
                        }
                    }
                }
            }
        }
    }
}
