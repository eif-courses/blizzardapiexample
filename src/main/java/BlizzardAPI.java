import model.AccessToken;
import model.ItemType;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BlizzardAPI {

  @Headers("Accept: application/json")
  @POST("/oauth/token")
  @FormUrlEncoded
  Call<AccessToken> getAccessToken(@Field("client_id") String client_id,
                                   @Field("client_secret") String client_secret,
                                   @Field("grant_type") String grant_type);

  @Headers("Accept: application/json")
  @GET("/d3/data/item-type")
  Call<List<ItemType>> getItemTypes(@Query("locale") String locale,
                                    @Query("access_token") String access_token);


}
