package com.lbe.imsdk.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbe.imsdk.R
import com.lbe.imsdk.ui.theme.primaryColor

/**
 *
 * @Author mocaris
 * @Date 2026-01-19
 * @Since
 */

data class DialogAction(
    val onClick: (() -> Unit),
    val content: @Composable RowScope.() -> Unit,
)

@Composable
fun IMCupertinoDialogContent(
    title: (@Composable () -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
    actions: List<DialogAction>? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (null != title) {
                CompositionLocalProvider(
                    LocalTextStyle provides MaterialTheme
                        .typography
                        .bodyLarge
                        .copy(fontWeight = FontWeight.SemiBold),
                    title
                )
            }
            if (null != content) {
                //R.color.FF76767A

                CompositionLocalProvider(
                    LocalTextStyle provides MaterialTheme.typography.bodyMedium.copy(
                        color = colorResource(R.color.FF76767A),
                    ),
                    content
                )
            }
        }
        if (null != actions) {
            HorizontalDivider(thickness = 0.5.dp, color = colorResource(R.color.divider))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                horizontalArrangement = Arrangement.spacedBy(0.5.dp),
                content = {
                    actions.forEachIndexed { index, action ->
                        DialogsAction(
                            onClick = action.onClick,
                            content = action.content
                        )
                        if (index < actions.lastIndex) {
                            VerticalDivider(
                                modifier = Modifier.fillMaxHeight(),
                                color = colorResource(R.color.divider),
                                thickness = 0.5.dp
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.DialogsAction(
    onClick: () -> Unit, content: @Composable RowScope.() -> Unit
) {
    TextButton(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight(),
        onClick = onClick,
        shape = RoundedCornerShape(0.dp),
        content = content,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}