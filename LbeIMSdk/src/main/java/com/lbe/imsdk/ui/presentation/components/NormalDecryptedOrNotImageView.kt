package com.lbe.imsdk.ui.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders

import coil3.request.ImageRequest
import com.lbe.imsdk.ui.presentation.viewmodel.ChatScreenViewModel


@Composable
fun NormalDecryptedOrNotImageView(
    key: String,
    url: String,
    modifier: Modifier,
    imageLoader: ImageLoader,
    contentScale: ContentScale = ContentScale.FillWidth,
    placeholder: Painter? = null,
    error: Painter? = null,
) {
    val ctx = LocalPlatformContext.current
    AsyncImage(
        model = ImageRequest.Builder(ctx).data(url)
            .httpHeaders(NetworkHeaders.Builder().apply {
                set("lbeToken", ChatScreenViewModel.lbeToken)
            }.build())
            .decoderFactory(
                DecryptedDecoder.Factory(url = url, key = key)
            ).build(),
        contentDescription = "Yo",
        contentScale = contentScale,
        modifier = modifier,
        error = error,
        placeholder = placeholder,
        imageLoader = imageLoader,
    )
}