import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.*
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.PositionalThreshold
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import com.lbe.imsdk.R
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LightPullToRefreshList(
    modifier: Modifier,
    isRefreshing: Boolean,
    onRefresh: suspend () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val state = remember {
        object : PullToRefreshState {
            private val anim = Animatable(0f, Float.VectorConverter)

            override val distanceFraction get() = anim.value

            override suspend fun animateToThreshold() {
                anim.animateTo(1f)
            }

            override suspend fun animateToHidden() {
                anim.animateTo(0f)
            }

            override suspend fun snapTo(targetValue: Float) {
                anim.snapTo(targetValue)
            }
        }
    }
    PullToRefreshBox(
        modifier = modifier,
        isRefreshing = isRefreshing,
        state = state,
        onRefresh = {
            coroutineScope.launch {
                onRefresh()
            }
        },
        contentAlignment = Alignment.TopCenter,
        indicator = {
            Box(
                modifier =
                modifier.pullToRefreshIndicator(
                    state = state,
                    isRefreshing = isRefreshing,
                    containerColor = Color.Transparent,
                    threshold = PositionalThreshold,
                    elevation = 0.dp
                ),
                contentAlignment = Alignment.Center
            ) {
                RotatingImage(
                    modifier = Modifier
                        .size(30.dp),
                    imageRes = R.drawable.loding_new,
                )
            }
        },
        content = content
    )
}
/*

@Composable
fun LightPullToRefreshList(
    modifier: Modifier,
    listState: LazyListState,
    onRefresh: suspend () -> Unit,
    lazyColumn: @Composable ColumnScope.() -> Unit,
    onScroll: (Float) -> Unit,
) {
    var refreshing by remember { mutableStateOf(false) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    val maxOffset = 110f
    val coroutineScope = rememberCoroutineScope()

    // Offset 动画
    val animatedOffsetY by animateFloatAsState(
        targetValue = offsetY, animationSpec = spring(), label = "animatedOffsetY"
    )

    // 指示器的透明度和尺寸
    val indicatorAlpha by animateFloatAsState(
        targetValue = (offsetY / maxOffset).coerceIn(0f, 1f), label = "indicatorAlpha"
    )
    val indicatorSize by animateFloatAsState(
        targetValue = (offsetY / maxOffset * 19).coerceIn(10f, 19f), label = "indicatorSize"
    )

    // 处理释放或快速滑动后的逻辑
    fun handleFlingOrRelease() {
//        println("手势回收 ---->>> isDragging: $isDragging, refreshing: $refreshing, offsetY: $offsetY")
        if (!isDragging && !refreshing) {
            if (offsetY >= maxOffset) {
                // 触发刷新
                refreshing = true
                coroutineScope.launch {
                    onRefresh()
                    delay(500)
                    refreshing = false
                    offsetY = 0f // 刷新结束后回弹
                }
            } else {
                // 未达到阈值，直接回弹
                offsetY = 0f
            }
        }
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (!refreshing && available.y > 0 && listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0) {
                    offsetY = (offsetY + available.y).coerceIn(0f, maxOffset * 1f)
                    isDragging = true
                    return Offset(available.x, available.y)
                }
                // 非下拉刷新逻辑，传递滚动偏移量给外部
                onScroll(available.y)
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset, available: Offset, source: NestedScrollSource
            ): Offset {
                if (!refreshing && available.y < 0) {
                    offsetY = (offsetY + available.y).coerceAtLeast(0f)
                }
                onScroll(available.y) // 传递滚动偏移量给外部
                return Offset.Zero
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
//                println("手势 onPreFling --->>> offsetY: $offsetY, refreshing: $refreshing")
                isDragging = false
                handleFlingOrRelease()
                return Velocity.Zero
            }
        }
    }

    // 用于检测拖动结束后的状态
    LaunchedEffect(isDragging) {
        if (!isDragging) {
            handleFlingOrRelease()
        }
    }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .nestedScroll(nestedScrollConnection)
    ) {

        Column(modifier = modifier
            .fillMaxHeight()
            .offset { IntOffset(0, animatedOffsetY.roundToInt()) }) {
            lazyColumn()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(33.dp)
                .align(Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            RotatingImage(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .size(indicatorSize.dp)
                    .alpha(indicatorAlpha),
                imageRes = R.drawable.loding_new,
            )
        }
    }
}
*/

@Composable
fun RotatingImage(modifier: Modifier = Modifier, imageRes: Int) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "rotation"
    )
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = modifier.graphicsLayer(rotationZ = rotation) // 应用旋转动画
    )
}
