package com.example.voiceapp

import org.vosk.Model
import org.vosk.Recognizer
import org.vosk.android.SpeechService
import org.vosk.android.StorageService
import java.io.IOException

class VoiceControlService : AccessibilityService(), org.vosk.android.RecognitionListener {

    private var voskModel: Model? = null
    private var voskService: SpeechService? = null

    override fun onServiceConnected() {
        super.onServiceConnected()
        initVosk()
    }

    private fun initVosk() {
        // Vosk needs to unpack the model from assets to internal storage first
        StorageService.unpack(this, "model-en-us", "model",
            { model -> 
                voskModel = model
                startWakeWordDetection()
            },
            { exception -> /* Handle error */ }
        )
    }

    private fun startWakeWordDetection() {
        val rec = Recognizer(voskModel, 16000.0f)
        // We can tell Vosk to only look for specific words to save CPU
        rec.setWords(true) 
        
        voskService = SpeechService(rec, 16000.0f)
        voskService?.startListening(this)
    }


    // --- Vosk Listener Methods ---
    override fun onResult(hypothesis: String) {
        // This is where you check for your wake word
        if (hypothesis.contains("hey device")) {
            voskService?.stop() // Stop Vosk to free mic for system SpeechRecognizer
            startActiveListening() // The function we wrote earlier
        }
    }
    
override fun onResult(hypothesis: String) {
    if (hypothesis.contains("hey device")) {
        Toast.makeText(this, "Wake word detected!", Toast.LENGTH_SHORT).show()
        voskService?.stop()
        startActiveListening()
    }
}

    override fun onPartialResult(hypothesis: String) {}
    override fun onFinalResult(hypothesis: String) {}
    override fun onError(exception: Exception) {}
    override fun onTimeout() {}
}
