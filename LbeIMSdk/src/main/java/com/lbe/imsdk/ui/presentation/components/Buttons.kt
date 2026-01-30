package com.lbe.imsdk.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbe.imsdk.R
import com.lbe.imsdk.ui.theme.customButtonBorderColor

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


@Composable
fun StartCustomerServiceButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, customButtonBorderColor, RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClick
            )
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(R.drawable.ic_cs),
            contentDescription = "service"
        )
        Text(text = stringResource(R.string.chat_session_status_34), fontSize = 12.sp)

    }
}

@Composable
fun CloseCustomerServiceButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .size(34.dp)
            .clip(
                CircleShape
            ),
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(R.drawable.ic_close),
            contentDescription = "close"
        )

    }
}