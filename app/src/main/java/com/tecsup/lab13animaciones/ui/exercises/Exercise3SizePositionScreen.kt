package com.tecsup.lab13animaciones.ui.exercises

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.lab13animaciones.ui.theme.Lab13Theme

/**
 * Ejercicio 3: Animación de tamaño y posición.
 *
 * animateDpAsState anima el tamaño del cuadro y sus desplazamientos en X e Y.
 * Modifier.offset aplica esos desplazamientos. El selector permite cambiar
 * el orden de los modificadores para observar cómo influye en el resultado.
 */
@Composable
fun Exercise3SizePositionScreen(modifier: Modifier = Modifier) {
    var expandido by rememberSaveable { mutableStateOf(false) }
    var offsetPrimero by rememberSaveable { mutableStateOf(true) }

    val tamano by animateDpAsState(
        targetValue = if (expandido) 140.dp else 70.dp,
        animationSpec = tween(durationMillis = 700),
        label = "tamano"
    )
    val desplazamientoX by animateDpAsState(
        targetValue = if (expandido) 120.dp else 0.dp,
        animationSpec = tween(durationMillis = 700),
        label = "desplazamientoX"
    )
    val desplazamientoY by animateDpAsState(
        targetValue = if (expandido) 80.dp else 0.dp,
        animationSpec = tween(durationMillis = 700),
        label = "desplazamientoY"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ejercicio 3",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "animateDpAsState + Modifier.offset",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Pulsa el botón para mover y cambiar de tamaño el cuadro.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(16.dp)
        ) {
            val modificadorBase = if (offsetPrimero) {
                Modifier
                    .offset(x = desplazamientoX, y = desplazamientoY)
                    .size(tamano)
            } else {
                Modifier
                    .size(tamano)
                    .offset(x = desplazamientoX, y = desplazamientoY)
            }

            Box(
                modifier = modificadorBase
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF3D4EA8), Color(0xFF00897B))
                        )
                    )
                    .border(
                        width = 2.dp,
                        color = Color.White.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${tamano.value.toInt()} dp",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tamaño: ${tamano.value.toInt()} dp  ·  X: ${desplazamientoX.value.toInt()} dp  ·  Y: ${desplazamientoY.value.toInt()} dp",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Orden de los modificadores",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FilterChip(
                selected = offsetPrimero,
                onClick = { offsetPrimero = true },
                label = { Text("offset → size") }
            )
            FilterChip(
                selected = !offsetPrimero,
                onClick = { offsetPrimero = false },
                label = { Text("size → offset") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { expandido = !expandido }) {
            Text(text = if (expandido) "Volver al inicio" else "Mover y cambiar tamaño")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Exercise3SizePositionPreview() {
    Lab13Theme {
        Exercise3SizePositionScreen()
    }
}
