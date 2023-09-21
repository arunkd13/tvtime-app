package com.datasnap.tvtime.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabRow
import androidx.tv.material3.Text
import com.datasnap.tvtime.ui.theme.AppTheme

/**
 * Restores focus to the selected tab when focus moves to the tab row. The default TabRow's
 * behaviour is to focus the tab that is nearest to the element from where the focus moves.
 * This is not what we want as focus itself selects the tab and results in the content changing
 * to a tab even before the user has chosen to change it.
 */
@OptIn(ExperimentalComposeUiApi::class, ExperimentalTvMaterial3Api::class)
@Composable
fun TvTimeTabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTabFocusRequester = remember { FocusRequester() }
    TabRow(selectedTabIndex = selectedTabIndex,
        modifier = modifier.focusProperties {
            enter = {
                selectedTabFocusRequester
            }
        }
    ) {
        tabs.forEachIndexed { i, name ->
            Tab(
                selected = i == selectedTabIndex,
                onFocus = { if (i != selectedTabIndex) onTabSelected(i) },
                modifier = if (i == selectedTabIndex) Modifier.focusRequester(
                    selectedTabFocusRequester
                ) else Modifier
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                )
            }
        }
    }
}

@Preview
@Composable
fun TvTimeTabRowPreview() {
    AppTheme {
        TvTimeTabRow(tabs = listOf("One", "Two", "Three"), selectedTabIndex = 1, onTabSelected = {})
    }
}
