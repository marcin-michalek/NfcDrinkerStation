/**
 * Created by Marcin Michałek on 2015-01-03.
 *
 */
package pl.michalek.marcin.nfcdrinkerstation.activity;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * TODO Add class description...
 *
 * @author Marcin Michałek
 */
public class BaseActivity extends Activity {
  protected boolean isIntentActionNDEFDiscovered() {
    return NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction());
  }

  protected List<Parcelable> getExtraNDEFMessages() {
    if (getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES) != null) {
      return new ArrayList<>(Arrays.asList(getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)));
    }
    return Collections.emptyList();
  }
}
