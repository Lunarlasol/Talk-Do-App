package com.example.voicecontrol

import android.accessibilityservice.AccessibilityService
import android.widget.Toast

class VoiceControlService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Handle accessibility events here
    }

    override fun onInterrupt() {
        // Handle interruption
    }

    // Removed duplicate onResult method
    fun onResult(result: String) {
        // Handle the result here
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    // Implementing startActiveListening function
    fun startActiveListening() {
        // Logic to start active listening goes here
    }
}