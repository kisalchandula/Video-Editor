package com.kisal.viedeoeditor.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kisal.viedeoeditor.misc.setImmersiveMode

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            setImmersiveMode(false)
        }
    }
}
