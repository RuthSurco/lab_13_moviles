package com.tecsup.lab13animaciones.ui.exercises

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.lab13animaciones.ui.theme.Lab13Theme

private val Azul = Color(0xFF3D4EA8)
private val Verde = Color(0xFF00897B)

/**
 * Ejercicio 2: Cambio de color con animateColorAsState.
 *
 * El botón cambia el color objetivo entre azul y verde. animateColorAsState
 * interpola el color actual hacia el nuevo valor, creando una transición
 * suave. Se puede elegir entre una animación tween o spring.
 */
@Composable
fun Exercise2ColorScreen(modifier: Modifier = Modifier) {
    var esVerde by rememberSaveable { mutableStateOf(false) }
    var usarSpring by rememberSaveable { mutableStateOf(false) }

    val colorObjetivo = if (esVerde) Verde else Azul
    val colorAnimado by animateColorAsState(
        targetValue = colorObjetivo,
        animationSpec = if (usarSpring) {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        } else {
            tween(durationMillis = 900)
        },
        label = "colorDeFondo"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ejercicio 2",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "animateColorAsState",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Elige una especificación y pulsa el botón para ver cómo cambia el color del cuadro.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(28.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(colorAnimado),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (esVerde) "Verde" else "Azul",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "#%06X".format(colorAnimado.toArgb() and 0xFFFFFF),
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Especificación de animación",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FilterChip(
                selected = !usarSpring,
                onClick = { usarSpring = false },
                label = { Text("Tween") }
            )
            FilterChip(
                selected = usarSpring,
                onClick = { usarSpring = true },
                label = { Text("Spring") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { esVerde = !esVerde }) {
            Text(text = if (esVerde) "Cambiar a azul" else "Cambiar a verde")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Exercise2ColorPreview() {
    Lab13Theme {
        Exercise2ColorScreen()
    }
}
