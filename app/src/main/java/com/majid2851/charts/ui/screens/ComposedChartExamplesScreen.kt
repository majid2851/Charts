package com.majid2851.charts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.majid2851.charts.ui.components.composed.variants.*

@Composable
fun ComposedChartExamplesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Composed Chart Examples",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // 1. Line Bar Area Composed Chart
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            LineBarAreaComposedChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        // 2. Same Data Composed Chart
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            SameDataComposedChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        // 3. Vertical Composed Chart
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            VerticalComposedChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        // 4. Composed Chart With Axis Labels
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            ComposedChartWithAxisLabels(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        // 5. Scatter And Line Of Best Fit
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            ScatterAndLineOfBestFit(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        // 6. Banded Chart
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            BandedChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        // 7. Target Price Chart
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            TargetPriceChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

