/**
 * Created by Marcin Michałek on 2015-01-31.
 *
 */
package pl.michalek.marcin.nfcdrinkerstation.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import java.util.HashMap;
import java.util.Locale;

/**
 * TODO Add class description...
 *
 * @author Marcin Michałek
 */
public class BaseTextToSpeechActivity extends BaseRestActivity implements TextToSpeech.OnInitListener {

  protected TextToSpeech textToSpeech;
  protected boolean textToSpeechReady;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    textToSpeech = new TextToSpeech(this, this);
    checkIfTextToSpeechInstalled();
  }

  private void checkIfTextToSpeechInstalled() {
    Intent check = new Intent();
    check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
    startActivityForResult(check, 5006);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 5006) {
      if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {

      } else {
        Intent install = new Intent();
        install.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
        startActivity(install);
      }
    }
  }

  @Override
  public void onInit(int status) {
    if (TextToSpeech.SUCCESS == status) {
      Locale.getAvailableLocales();
      textToSpeech.setLanguage(Locale.getDefault());
      textToSpeechReady = true;
    } else {
      textToSpeechReady = false;
    }
  }

  protected void speak(String text) {
    if (textToSpeechReady) {
      HashMap<String, String> hash = new HashMap<>();
      hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
          String.valueOf(AudioManager.STREAM_NOTIFICATION));
      textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, hash);
    }
  }

  @Override
  protected void onStop() {
    if (textToSpeech != null) {
      textToSpeech.shutdown();
      textToSpeech = null;
    }

    super.onStop();
  }
}
