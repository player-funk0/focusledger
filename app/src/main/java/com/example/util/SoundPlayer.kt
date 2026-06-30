package com.example.util

import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log

import android.content.Context
import android.media.MediaPlayer

object SoundPlayer {
    private const val TAG = "SoundPlayer"
    private var mediaPlayer: MediaPlayer? = null

    /**
     * Starts playing calm music for focus sessions.
     * In a real implementation, this would load a local MP3 asset.
     * For this mock, we use ToneGenerator to simulate a calm rhythmic pulse.
     */
    fun startFocusMusic(context: Context, customUri: String? = null) {
        stopMusic()
        
        if (customUri != null) {
            try {
                val uri = android.net.Uri.parse(customUri)
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(context, uri)
                    setAudioStreamType(AudioManager.STREAM_MUSIC)
                    isLooping = true
                    prepare()
                    start()
                }
                return
            } catch (e: Exception) {
                Log.e(TAG, "Error playing custom music URI: $customUri", e)
                // Fallback to default simulation if custom music fails
            }
        }

        // Default calm focus music simulation
        Thread {
            try {
                val toneGen = ToneGenerator(AudioManager.STREAM_MUSIC, 50)
                // We use a flag to stop the thread
                while (isMusicActive) { 
                    toneGen.startTone(ToneGenerator.TONE_CDMA_SOFT_ERROR_LITE, 1000)
                    Thread.sleep(2000)
                }
                toneGen.release()
            } catch (e: Exception) {
                Log.e(TAG, "Error playing focus music", e)
            }
        }.start()
        
        isMusicActive = true
    }

    private var isMusicActive = false

    fun stopMusic() {
        isMusicActive = false
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) {
            Log.e(TAG, "Error stopping music", e)
        }
    }

    // Deprecated alarm sound function
    fun playSound(soundName: String) {
        // Ringtones removed as per user request
    }
}
