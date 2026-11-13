package com.tecsup.lab13animaciones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.tecsup.lab13animaciones.ui.exercises.Exercise1VisibilityScreen
import com.tecsup.lab13animaciones.ui.exercises.Exercise2ColorScreen
import com.tecsup.lab13animaciones.ui.exercises.Exercise3SizePositionScreen

/**
 * Contenedor principal del laboratorio. Permite navegar entre los ejercicios
 * a medida que se van implementando.
 */
@Composable
fun Lab13App() {
    var selectedTab by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        PrimaryTabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Ejercicio 1") }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Ejercicio 2") }
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = { Text("Ejercicio 3") }
            )
        }

        when (selectedTab) {
            0 -> Exercise1VisibilityScreen()
            1 -> Exercise2ColorScreen()
            else -> Exercise3SizePositionScreen()
        }
    }
}
