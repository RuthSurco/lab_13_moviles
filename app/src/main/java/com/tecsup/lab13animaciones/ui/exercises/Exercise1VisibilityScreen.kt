package com.tecsup.lab13animaciones.ui.exercises

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
 * Ejercicio 1: Animación de visibilidad con AnimatedVisibility.
 *
 * El botón alterna el estado [visible]. AnimatedVisibility se encarga de
 * animar la entrada (fadeIn + scaleIn) y la salida (fadeOut + scaleOut)
 * del cuadro de color.
 */
@Composable
fun Exercise1VisibilityScreen(modifier: Modifier = Modifier) {
    var visible by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ejercicio 1",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "AnimatedVisibility",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Pulsa el botón para mostrar u ocultar el cuadro con una transición suave.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Contenedor de altura fija: evita que el botón "salte" cuando
        // el cuadro se oculta y el espacio se colapsa.
        Box(
            modifier = Modifier.height(220.dp),
            contentAlignment = Alignment.Center
        ) {
            VisibilityDemoBox(visible = visible)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { visible = !visible }) {
            Text(text = if (visible) "Ocultar cuadro" else "Mostrar cuadro")
        }
    }
}

@Composable
private fun VisibilityDemoBox(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(durationMillis = 600)) +
            scaleIn(initialScale = 0.85f, animationSpec = tween(durationMillis = 600)),
        exit = fadeOut(animationSpec = tween(durationMillis = 400)) +
            scaleOut(targetScale = 0.85f, animationSpec = tween(durationMillis = 400))
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFF3D4EA8), Color(0xFF00897B))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "¡Hola\nCompose!",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Exercise1VisibilityPreview() {
    Lab13Theme {
        Exercise1VisibilityScreen()
    }
}
