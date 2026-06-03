package ir.hasanazimi.devlab.presentation.ui_kit.tab_row

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ir.hasanazimi.devlab.presentation.theme.AppTheme


data class TabItem(
    val tabTitle: String,
    val content: @Composable () -> Unit
)


@Composable
fun BaseTabRow(
    modifier: Modifier = Modifier,
    pagerState : PagerState,
    tabItems: List<TabItem>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = MaterialTheme.colorScheme.primary
                )
            },
            divider = {}
        ) {
            tabItems.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = tab.tabTitle,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 11.5.sp,
                                textAlign = TextAlign.Center
                            ),
                            fontWeight = if (selectedTabIndex == index)
                                FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTabIndex == index)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onPrimary
                        )
                    }
                )
            }
        }



        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
        ) { page ->
            Log.i("HorizontalPager", "BaseTabRow: page -> $page ")
            tabItems[page].content()
        }

    }
}



@Composable
private fun TEST1() {
    Box(modifier = Modifier.fillMaxSize()){}
}
@Composable
private fun TEST2() {
    Box(modifier = Modifier.fillMaxSize()){}
}
@Composable
private fun TEST3() {
    Box(modifier = Modifier.fillMaxSize()){}
}

@Preview
@Composable
private fun BaseTabRowPreview() {

    val tabItems = mutableListOf<TabItem>()
    tabItems.add(TabItem("تست 1" , { TEST1() }))
    tabItems.add(TabItem("تست 2" , { TEST2() }))
    tabItems.add(TabItem("تست 3" , { TEST3() }))

    AppTheme {
        BaseTabRow(
            modifier = Modifier,
            pagerState = rememberPagerState(initialPage = 0, pageCount = {3}),
            tabItems = tabItems,
            selectedTabIndex = 0,
            onTabSelected = { index ->
                // todo
            }
        )
    }
}