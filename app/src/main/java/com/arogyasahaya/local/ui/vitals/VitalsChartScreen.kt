package com.arogyasahaya.local.ui.vitals

import android.graphics.Color
import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.arogyasahaya.local.data.local.entity.VitalLogEntity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalsChartScreen(
    onNavigateBack: () -> Unit,
    viewModel: VitalsViewModel = hiltViewModel()
) {
    val logs by viewModel.last7DaysLogs.collectAsState()
    val sortedLogs = logs.sortedBy { it.date }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "7-Day Trend",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        if (sortedLogs.size < 2) {
            EmptyChartState(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                ChartInfoCard(logs = sortedLogs)

                Spacer(modifier = Modifier.height(16.dp))

                LineChartView(logs = sortedLogs)

                Spacer(modifier = Modifier.height(16.dp))

                ChartLegend()
            }
        }
    }
}

@Composable
private fun EmptyChartState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(48.dp)
        ) {
            Text(
                text = "📊",
                fontSize = 72.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Not Enough Data Yet",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Log your vitals for at least 2 different days to see your health trend chart.",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
private fun ChartInfoCard(logs: List<VitalLogEntity>) {
    val dateFormat = SimpleDateFormat("MMM d", Locale.getDefault())
    val firstDate = dateFormat.format(Date(logs.first().date))
    val lastDate = dateFormat.format(Date(logs.last().date))

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Health Trends",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$firstDate - $lastDate (${logs.size} entries)",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun LineChartView(logs: List<VitalLogEntity>) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp)),
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false
                setTouchEnabled(true)
                isDragEnabled = true
                setScaleEnabled(true)
                setPinchZoom(true)
                isDoubleTapToZoomEnabled = true

                setDrawGridBackground(false)
                setDrawBorders(false)

                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.setDrawAxisLine(true)
                xAxis.textColor = Color.parseColor("#6B7280")
                xAxis.textSize = 12f
                xAxis.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)

                axisLeft.setDrawGridLines(true)
                axisLeft.gridColor = Color.parseColor("#E5E7EB")
                axisLeft.textColor = Color.parseColor("#6B7280")
                axisLeft.textSize = 11f
                axisLeft.setDrawAxisLine(false)

                axisRight.isEnabled = false

                legend.isEnabled = false

                setExtraOffsets(16f, 16f, 16f, 16f)

                animateX(800)
                animateY(800)
            }
        },
        update = { chart ->
            val data = createLineData(logs)
            chart.data = data
            
            // Set XAxis labels
            val dateFormat = SimpleDateFormat("EEE", Locale.getDefault())
            val xLabels = logs.map { dateFormat.format(Date(it.date)) }
            chart.xAxis.valueFormatter = object : com.github.mikephil.charting.formatter.ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val index = value.toInt()
                    return if (index >= 0 && index < xLabels.size) {
                        xLabels[index]
                    } else {
                        ""
                    }
                }
            }
            chart.xAxis.labelCount = xLabels.size
            
            chart.invalidate()
        }
    )
}

private fun createLineData(logs: List<VitalLogEntity>): LineData {
    val systolicEntries = mutableListOf<Entry>()
    val heartRateEntries = mutableListOf<Entry>()
    val glucoseEntries = mutableListOf<Entry>()

    logs.forEachIndexed { index, log ->
        val xValue = index.toFloat()

        log.bloodPressureSystolic?.let {
            systolicEntries.add(Entry(xValue, it.toFloat()))
        }

        log.heartRate?.let {
            heartRateEntries.add(Entry(xValue, it.toFloat()))
        }

        log.glucoseLevel?.let {
            glucoseEntries.add(Entry(xValue, it))
        }
    }

    val dataSets = mutableListOf<LineDataSet>()

    if (systolicEntries.isNotEmpty()) {
        val systolicSet = LineDataSet(systolicEntries, "Systolic BP").apply {
            color = Color.parseColor("#D32F2F")
            setCircleColor(Color.parseColor("#D32F2F"))
            circleRadius = 6f
            circleHoleRadius = 3f
            lineWidth = 2.5f
            valueTextColor = Color.parseColor("#D32F2F")
            valueTextSize = 11f
            setDrawFilled(true)
            fillColor = Color.parseColor("#40D32F2F")
            mode = LineDataSet.Mode.CUBIC_BEZIER
            cubicIntensity = 0.2f
        }
        dataSets.add(systolicSet)
    }

    if (heartRateEntries.isNotEmpty()) {
        val heartRateSet = LineDataSet(heartRateEntries, "Heart Rate").apply {
            color = Color.parseColor("#1976D2")
            setCircleColor(Color.parseColor("#1976D2"))
            circleRadius = 6f
            circleHoleRadius = 3f
            lineWidth = 2.5f
            valueTextColor = Color.parseColor("#1976D2")
            valueTextSize = 11f
            setDrawFilled(true)
            fillColor = Color.parseColor("#401976D2")
            mode = LineDataSet.Mode.CUBIC_BEZIER
            cubicIntensity = 0.2f
        }
        dataSets.add(heartRateSet)
    }

    if (glucoseEntries.isNotEmpty()) {
        val glucoseSet = LineDataSet(glucoseEntries, "Glucose").apply {
            color = Color.parseColor("#F57C00")
            setCircleColor(Color.parseColor("#F57C00"))
            circleRadius = 6f
            circleHoleRadius = 3f
            lineWidth = 2.5f
            valueTextColor = Color.parseColor("#F57C00")
            valueTextSize = 11f
            setDrawFilled(true)
            fillColor = Color.parseColor("#40F57C00")
            mode = LineDataSet.Mode.CUBIC_BEZIER
            cubicIntensity = 0.2f
        }
        dataSets.add(glucoseSet)
    }

    return LineData(dataSets.toList())
}

@Composable
private fun ChartLegend() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LegendItem(color = Color.parseColor("#D32F2F"), label = "Systolic BP (mmHg)")
        LegendItem(color = Color.parseColor("#1976D2"), label = "Heart Rate (bpm)")
        LegendItem(color = Color.parseColor("#F57C00"), label = "Glucose (mg/dL)")
    }
}

@Composable
private fun LegendItem(color: Int, label: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(ComposeColor(color), RoundedCornerShape(4.dp))
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
    }
}
