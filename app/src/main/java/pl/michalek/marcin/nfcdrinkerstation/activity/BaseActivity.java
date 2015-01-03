/**
 * Created by Marcin Michałek on 2015-01-03.
 *
 */
package pl.michalek.marcin.nfcdrinkerstation.activity;

import android.app.Activity;
import android.nfc.NfcAdapter;

/**
 * TODO Add class description...
 *
 * @author Marcin Michałek
 */
public class BaseActivity extends Activity {
  protected boolean isIntentActionNDEFDiscovored() {
    return NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction());
  }
}
