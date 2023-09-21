package com.datasnap.tvtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.datasnap.tvtime.ui.components.TvTimeTabRow
import com.datasnap.tvtime.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    TvTimeTabRow(
        tabs = listOf("One", "Two", "Three"),
        selectedTabIndex = selectedTabIndex,
        onTabSelected = { i -> selectedTabIndex = i })
}
