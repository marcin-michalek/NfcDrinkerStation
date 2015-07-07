/**
 * Created by Marcin Michałek on 2015-01-02.
 *
 */
package pl.michalek.marcin.nfcdrinkerstation.network;

import android.util.Log;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import pl.michalek.marcin.nfcdrinkerstation.config.Constants;

/**
 *
 * @author Marcin Michałek
 */
public abstract class BaseNonContextRequestListener<T> implements RequestListener<T> {

  @Override
  public void onRequestFailure(SpiceException spiceException) {
    Log.e(Constants.LOGTAG, spiceException.getMessage(), spiceException);
  }

  @Override
  public abstract void onRequestSuccess(T t);
}
