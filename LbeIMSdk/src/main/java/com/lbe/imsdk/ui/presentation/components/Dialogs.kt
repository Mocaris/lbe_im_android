package com.lbe.imsdk.ui.presentation.components

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
                Text(stringResource(R.string.confirm_end_service))
            },
            content = {
                Text(stringResource(R.string.end_service_tip))
            },
            actions = listOf(
                DialogAction(onClick = onDismissRequest) {
                    Text(stringResource(R.string.cancel), color = Color.Gray)
                },
                DialogAction(onClick = endService) {
                    Text(stringResource(R.string.end_service))
                }
            )
        )
    }
}