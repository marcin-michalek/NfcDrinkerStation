/**
 * Created by Marcin Michałek on 2015-01-02.
 *
 */
package pl.michalek.marcin.nfcdrinkerstation.network.request;

import android.util.Log;
import com.google.gson.Gson;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import pl.michalek.marcin.nfcdrinkerstation.network.ServicePaths;
import pl.michalek.marcin.nfcdrinkerstation.network.model.Drinker;

/**
 * TODO Add class description...
 *
 * @author Marcin Michałek
 */
public class SaveDrinkerRequest extends SpringAndroidSpiceRequest<String> {
  private Drinker drinker;

  public SaveDrinkerRequest(Drinker drinker) {
    super(String.class);
    this.drinker = drinker;
  }

  @Override
  public String loadDataFromNetwork() throws Exception {
    return getRestTemplate().postForObject(ServicePaths.REST_ENDPOINT + "drink", drinker, String.class);
  }
}
