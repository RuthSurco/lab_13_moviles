package com.tecsup.lab13animaciones.ui.exercises

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.lab13animaciones.ui.theme.Lab13Theme
import kotlinx.coroutines.delay

/** Los tres estados posibles del contenido. */
private enum class EstadoContenido(val titulo: String) {
    Cargando("Cargando"),
    Contenido("Contenido"),
    Error("Error")
}

/**
 * Ejercicio 4: Cambio de contenido con AnimatedContent.
 *
 * AnimatedContent recibe el estado actual y muestra el contenido correspondiente.
 * Cuando el estado cambia, la salida del contenido anterior y la entrada del nuevo
 * se animan con fadeOut y fadeIn, cada una con su propia duración.
 */
@Composable
fun Exercise4AnimatedContentScreen(modifier: Modifier = Modifier) {
    var estado by rememberSaveable { mutableStateOf(EstadoContenido.Cargando) }
    var cargaAutomatica by rememberSaveable { mutableStateOf(false) }

    // Simula una carga real: pasa de Cargando a Contenido después de 2 segundos.
    LaunchedEffect(cargaAutomatica, estado) {
        if (cargaAutomatica && estado == EstadoContenido.Cargando) {
            delay(2000)
            estado = EstadoContenido.Contenido
            cargaAutomatica = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ejercicio 4",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "AnimatedContent",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Cambia entre los estados y observa la transición de entrada y salida.",
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
        ) {
            AnimatedContent(
                targetState = estado,
                transitionSpec = {
                    // El contenido nuevo entra con 600 ms y el anterior sale con 300 ms.
                    fadeIn(animationSpec = tween(durationMillis = 600)) togetherWith
                        fadeOut(animationSpec = tween(durationMillis = 300))
                },
                contentAlignment = Alignment.Center,
                label = "estadoContenido"
            ) { estadoActual ->
                ContenidoEstado(estado = estadoActual, modifier = Modifier.fillMaxSize())
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Transición: fadeIn 600 ms · fadeOut 300 ms",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Estado",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            EstadoContenido.entries.forEach { opcion ->
                FilterChip(
                    selected = estado == opcion,
                    onClick = {
                        cargaAutomatica = false
                        estado = opcion
                    },
                    label = { Text(opcion.titulo) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {
                    estado = EstadoContenido.Cargando
                    cargaAutomatica = true
                }
            ) {
                Text("Simular carga")
            }
            OutlinedButton(
                onClick = {
                    cargaAutomatica = false
                    estado = EstadoContenido.Error
                }
            ) {
                Text("Simular error")
            }
        }
    }
}

/** Contenido que se muestra según el estado. Cada estado tiene su propio diseño. */
@Composable
private fun ContenidoEstado(estado: EstadoContenido, modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(24.dp), contentAlignment = Alignment.Center) {
        when (estado) {
            EstadoContenido.Cargando -> Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Cargando datos...",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Espera un momento por favor.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            EstadoContenido.Contenido -> Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "✅", fontSize = 40.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Contenido cargado",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Los datos se obtuvieron correctamente.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            EstadoContenido.Error -> Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "⚠️", fontSize = 40.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ocurrió un error",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "No se pudieron cargar los datos. Inténtalo otra vez.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Exercise4AnimatedContentPreview() {
    Lab13Theme {
        Exercise4AnimatedContentScreen()
    }
}
