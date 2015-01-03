package pl.michalek.marcin.nfcdrinkerstation.activity;

import android.media.MediaPlayer;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.gson.Gson;
import pl.michalek.marcin.nfcdrinkerstation.R;
import pl.michalek.marcin.nfcdrinkerstation.network.BaseNonContextRequestListener;
import pl.michalek.marcin.nfcdrinkerstation.network.model.Drinker;
import pl.michalek.marcin.nfcdrinkerstation.network.request.SaveDrinkerRequest;


public class MainActivity extends BaseRestActivity {
  @InjectView(R.id.welcomeText)
  TextView welcomeText;

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
    mediaPlayer.release();
    mediaPlayer = null;
    super.onStop();
  }

  public void onResume() {
    super.onResume();
    NdefMessage[] msgs;

    if (isIntentActionNDEFDiscovored()) {
      Parcelable[] rawMsgs = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
      if (rawMsgs != null) {
        msgs = new NdefMessage[rawMsgs.length];
        for (int i = 0; i < rawMsgs.length; i++) {
          msgs[i] = (NdefMessage) rawMsgs[i];
          logDrinker(new Gson().fromJson(new String(msgs[i].getRecords()[0].getPayload()), Drinker.class));
        }
      }
    }
  }

  private void logDrinker(Drinker drinker) {
    displayWelcomeMessage(drinker);
    saveOnServer(drinker);
  }

  private void displayWelcomeMessage(Drinker drinker) {
    welcomeText.setText("Bravo " + drinker.getName());
  }

  private void saveOnServer(Drinker drinker) {
    getSpiceManager().execute(new SaveDrinkerRequest(drinker), new BaseNonContextRequestListener<Boolean>() {
      @Override
      public void onRequestSuccess(Boolean success) {
        if (success) {
          playWinSound();
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
