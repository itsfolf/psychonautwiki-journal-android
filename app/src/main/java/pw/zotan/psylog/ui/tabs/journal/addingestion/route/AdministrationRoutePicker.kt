package pw.zotan.psylog.ui.tabs.journal.addingestion.route

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pw.zotan.psylog.data.substances.AdministrationRoute

@Composable
fun AdministrationRoutePicker(
    showOtherRoutes: Boolean,
    onChangeOfShowOtherRoutes: (Boolean) -> Unit,
    pwRoutes: List<AdministrationRoute>,
    otherRoutesChunked: List<List<AdministrationRoute>>,
    onRouteTapped: (route: AdministrationRoute) -> Unit,
) {
    val spacing = 6
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(spacing.dp)
    ) {
        val isShowingOther = showOtherRoutes || pwRoutes.isEmpty()
        AnimatedVisibility(
            visible = isShowingOther,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.dp)
            ) {
                otherRoutesChunked.forEach { otherRouteChunk ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(spacing.dp)
                    ) {
                        otherRouteChunk.forEach { route ->
                            ElevatedCard(
                                modifier = Modifier
                                    .clickable {
                                        onRouteTapped(route)
                                    }
                                    .fillMaxHeight()
                                    .weight(1f)
                            ) {
                                RouteBox(
                                    route = route,
                                    titleStyle = MaterialTheme.typography.headlineSmall
                                )
                            }
                        }
                        if (otherRouteChunk.size == 1) {
                            Box(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = !isShowingOther,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.dp),
            ) {
                pwRoutes.forEach { route ->
                    ElevatedCard(
                        modifier = Modifier
                            .clickable {
                                onRouteTapped(route)
                            }
                            .fillMaxWidth()
                            .weight(5f)
                    ) {
                        RouteBox(
                            route = route,
                            titleStyle = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
                ElevatedCard(
                    modifier = Modifier
                        .clickable {
                            onChangeOfShowOtherRoutes(true)
                        }
                        .fillMaxWidth()
                        .weight(5f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Other routes",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AdministrationRoutePickerPreview() {
    val pwRoutes = listOf(AdministrationRoute.INSUFFLATED, AdministrationRoute.ORAL)
    val otherRoutes = AdministrationRoute.entries.filter { route ->
        !pwRoutes.contains(route)
    }
    val otherRoutesChunked = otherRoutes.chunked(2)
    AdministrationRoutePicker(
        showOtherRoutes = false,
        onChangeOfShowOtherRoutes = {},
        pwRoutes = pwRoutes,
        otherRoutesChunked = otherRoutesChunked,
        onRouteTapped = {},
    )
}