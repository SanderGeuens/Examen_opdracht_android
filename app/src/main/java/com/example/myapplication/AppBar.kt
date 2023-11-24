package com.example.myapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.navigation.NavigationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar (
    modifier:Modifier = Modifier,
    currentScreen: NavigationRoutes,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    openDrawer: () -> Unit = {},
) {

    Surface(shadowElevation = 10.dp) {
        TopAppBar(
            title = {

                Text(stringResource(id = currentScreen.title))

            },
            actions = {
                Row(
                    modifier = Modifier
                        .padding(end = 16.dp, start = 16.dp)
                ) {
                    if (canNavigateBack) {
                        IconButton(
                            onClick = navigateUp,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                            /* Image(
                                painter = image,
                                contentDescription = "navigate back",
                            )*/
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = openDrawer) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            }
        )
    }
}