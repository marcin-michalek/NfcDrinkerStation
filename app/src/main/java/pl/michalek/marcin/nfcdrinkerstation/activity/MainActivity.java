package pl.michalek.marcin.nfcdrinkerstation.activity;

import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.gson.Gson;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import pl.michalek.marcin.nfcdrinkerstation.R;
import pl.michalek.marcin.nfcdrinkerstation.network.model.Drinker;
import pl.michalek.marcin.nfcdrinkerstation.network.request.SaveDrinkerRequest;


public class MainActivity extends BaseRestActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onResume() {
    super.onResume();
    NdefMessage[] msgs;

    if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
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

  private void logDrinker(Drinker drinker){
    getSpiceManager().execute(new SaveDrinkerRequest(drinker), new RequestListener<String>() {
      @Override
      public void onRequestFailure(SpiceException spiceException) {
        Log.d("TEST", spiceException.getMessage());
      }

      @Override
      public void onRequestSuccess(String s) {
        Log.d("TEST", s);
      }
    });
  }
}
