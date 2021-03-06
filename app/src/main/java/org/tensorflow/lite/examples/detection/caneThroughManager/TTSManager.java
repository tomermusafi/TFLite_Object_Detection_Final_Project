package org.tensorflow.lite.examples.detection.caneThroughManager;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TTSManager {

    private static TTSManager instance;
    private TextToSpeech tts;

    public static TTSManager getInstance() {
        return instance;
    }

    private TTSManager(Context _context) {

        tts = new TextToSpeech(_context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new TTSManager(context);
        }
    }

    public void speak(CharSequence text) {

        if (text == null || text.length() <= 0)
            return;
        if (tts.speak(text.toString(), TextToSpeech.QUEUE_FLUSH, null, null) == TextToSpeech.ERROR) {
            throw new RuntimeException("Text To Speech failed to speak");
        }

    }

    public boolean isSpeaking() {
        return tts.isSpeaking();
    }
}
