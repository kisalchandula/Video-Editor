package com.kisal.viedeoeditor.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import com.kisal.viedeoeditor.misc.setImmersiveMode
import com.kisal.viedeoeditor.misc.setupSystemUi
import com.kisal.viedeoeditor.settings.SettingsDataStore
import com.kisal.viedeoeditor.videoeditor.VideoEditorActivity

class MainActivity : ComponentActivity() {
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    private lateinit var pickProject: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSystemUi()

        val dataStore = SettingsDataStore(this)
        pickMedia =
            registerForActivityResult(CustomPickVisualMedia { dataStore.getLegacyFilePickerBlocking() }) { uri ->
                if (uri != null) {
                    launchVideoEditor(uri)
                }
            }
        pickProject = registerForActivityResult(
            CustomOpenDocument()
        ) { uri ->
            if (uri != null) {
                launchVideoEditor(uri)
            }
        }

        setContent {
            setImmersiveMode(false)
            MainScreen(pickMedia)
        }
    }

    @OptIn(UnstableApi::class)
    private fun launchVideoEditor(uri: Uri) {
        val intent = Intent(this, VideoEditorActivity::class.java)
        intent.action = Intent.ACTION_EDIT
        intent.data = uri
        startActivity(intent)
    }
}