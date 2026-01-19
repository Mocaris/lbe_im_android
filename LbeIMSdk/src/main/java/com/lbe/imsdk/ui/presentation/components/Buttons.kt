package com.lbe.imsdk.ui.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *
 * @Author mocaris
 * @Date 2026-01-19
 * @Since
 */

/// 人工客服 Button
@Composable
fun TurnCustomServiceButton(str: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .border(
                1.dp,
                Color.Black.copy(alpha = 0.5f),
                shape = RoundedCornerShape(corner = CornerSize(10.dp))
            )
            .padding(horizontal = 15.dp, vertical = 2.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            ),
    ) {
        Text(text = str, fontSize = 12.sp)
    }
}