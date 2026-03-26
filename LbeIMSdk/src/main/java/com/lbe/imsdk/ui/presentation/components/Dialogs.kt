package com.lbe.imsdk.ui.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.lbe.imsdk.R

/**
 *
 * @Author mocaris
 * @Date 2026-01-19
 * @Since
 */

@Composable
fun EndCustomServiceDialog(onDismissRequest: () -> Unit, endService: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        IMCupertinoDialogContent(
            title = {
                Text(stringResource(R.string.chat_session_status_31))
            },
            content = {
                Text(stringResource(R.string.chat_session_status_33))
            },
            actions = listOf(
                DialogAction(onClick = onDismissRequest) {
                    Text(stringResource(R.string.chat_session_status_32))
                },
                DialogAction(onClick = endService) {
                    Text(stringResource(R.string.chat_session_status_30), color = MaterialTheme.colorScheme.primary)
                }
            )
        )
    }
}