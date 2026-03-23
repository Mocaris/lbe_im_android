package com.lbe.imsdk.ui.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

/**
 *
 * @Author mocaris
 * @Date 2026-01-19
 * @Since
 */

@Composable
fun SystemMessageContent(content: String){
    Box(contentAlignment = Alignment.Center) {
        Text(
            content,
            style = TextStyle(
                color = Color(0xFF979797), fontSize = 12.sp, fontWeight = FontWeight.W400,
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}