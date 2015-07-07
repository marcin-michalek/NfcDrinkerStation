/**
 * Created by Marcin Michałek on 2015-01-02.
 *
 */
package pl.michalek.marcin.nfcdrinkerstation.network.request;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import pl.michalek.marcin.nfcdrinkerstation.network.ServicePaths;
import pl.michalek.marcin.nfcdrinkerstation.network.model.Drinker;

/**
 *
 * @author Marcin Michałek
 */
public class SaveDrinkerRequest extends SpringAndroidSpiceRequest<Boolean> {
  private Drinker drinker;

  public SaveDrinkerRequest(Drinker drinker) {
    super(Boolean.class);
    this.drinker = drinker;
  }

  @Override
  public Boolean loadDataFromNetwork() throws Exception {
    return getRestTemplate().postForObject(ServicePaths.REST_ENDPOINT + "drink", drinker, Boolean.class);
  }
}
