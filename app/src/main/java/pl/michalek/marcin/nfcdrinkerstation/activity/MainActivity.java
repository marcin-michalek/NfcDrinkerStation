package pl.michalek.marcin.nfcdrinkerstation.activity;

import android.media.MediaPlayer;
import android.nfc.NdefMessage;
import android.os.Bundle;
import android.os.Parcelable;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.gson.Gson;
import pl.michalek.marcin.nfcdrinkerstation.R;
import pl.michalek.marcin.nfcdrinkerstation.network.BaseNonContextRequestListener;
import pl.michalek.marcin.nfcdrinkerstation.network.model.Drinker;
import pl.michalek.marcin.nfcdrinkerstation.network.request.SaveDrinkerRequest;

import java.util.List;
import java.util.Locale;


public class MainActivity extends BaseTextToSpeechActivity {
  @InjectView(R.id.drinkTextView)
  TextView welcomeTextView;

  MediaPlayer mediaPlayer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
    mediaPlayer = MediaPlayer.create(this, R.raw.drink);
  }

  @Override
  protected void onStop() {
    if (mediaPlayer != null) {
      mediaPlayer.release();
      mediaPlayer = null;
    }
    super.onStop();
  }

  public void onResume() {
    super.onResume();
    if (isIntentActionNDEFDiscovered()) {
      List<Parcelable> rawMessagesList = getExtraNDEFMessages();
      if (!rawMessagesList.isEmpty()) {
        for (Parcelable message : rawMessagesList) {
          logDrinker(new Gson().fromJson(new String(((NdefMessage) message).getRecords()[0].getPayload()), Drinker.class));
        }
      }
    }
  }

  private void logDrinker(Drinker drinker) {
    displayWelcomeMessage(drinker);
    sayGreetings(drinker);
    saveOnServer(drinker);
  }

  private void sayGreetings(Drinker drinker) {
    speak("Na zdrowie " + drinker.getName());
  }

  private void displayWelcomeMessage(Drinker drinker) {
    welcomeTextView.setText(getString(R.string.cheers) + drinker.getName());
  }

  private void displayWelcomeMessage() {
    welcomeTextView.setText(getString(R.string.scan_a_tag));
  }

  private void saveOnServer(Drinker drinker) {
    getSpiceManager().execute(new SaveDrinkerRequest(drinker), new BaseNonContextRequestListener<Boolean>() {
      @Override
      public void onRequestSuccess(Boolean success) {
        if (success) {
          playWinSound();
          displayWelcomeMessage();
        }
      }
    });
  }

  private void playWinSound() {
    if (mediaPlayer != null) {
      mediaPlayer.start();
    }
  }
}
