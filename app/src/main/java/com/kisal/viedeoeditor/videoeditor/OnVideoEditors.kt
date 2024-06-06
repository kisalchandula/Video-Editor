package com.kisal.viedeoeditor.videoeditor

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.common.util.UnstableApi
import androidx.media3.effect.Crop
import com.kisal.viedeoeditor.misc.ResizableRectangle
import com.kisal.viedeoeditor.misc.screenToNdc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@OptIn(UnstableApi::class)
@Composable
fun CropEditor(effectFlow: MutableStateFlow<EffectConstructor?>) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val videoWidth = constraints.maxWidth.toFloat()
        val videoHeight = constraints.maxHeight.toFloat()
        ResizableRectangle(videoWidth, videoHeight, 0F, 0F) { width, height, x, y ->
            val left = screenToNdc(x, videoWidth)
            val right = screenToNdc(x + width, videoWidth)
            val bottom = screenToNdc(videoHeight - y - height, videoHeight)
            val top = screenToNdc(videoHeight - y, videoHeight)
            if (right > left && top > bottom) {
                effectFlow.update {
                    {
                        Crop(
                            left, right, bottom, top
                        )
                    }
                }
            }
        }
    }
}
