import model.AccessToken;
import model.ItemType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class BlizzardRepository {
  private static final String CLIENT_ID = "1c9dba3c1cdd4b5699c41b00c3ea680d";
  private static final String CLIENT_SECRET = "fbCtHRN4U8HQ5XAw6mzWvGtUHQzw1RyC";
  private static final String REDIRECT_URL = "https://localhost";
  private static final String BASE_URL = "https://eu.battle.net/";
  private static final String DIABLO3_BASE_URL = "https://eu.api.blizzard.com";
  private static final String GRANT_TYPE = "client_credentials";
  private static String token = "";

  public static void main(String[] args) {
    generateAccessToken();
    getItemTypesAndDisplay();
  }

  private static void getItemTypesAndDisplay() {
    Retrofit.Builder builder = new Retrofit.Builder()
        .baseUrl(DIABLO3_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();
    BlizzardAPI blizzardAPI = retrofit.create(BlizzardAPI.class);
    Call<List<ItemType>> call = blizzardAPI.getItemTypes("en_US", token);

    call.enqueue(new Callback<>() {
      @Override
      public void onResponse(Call<List<ItemType>> call, Response<List<ItemType>> response) {
        for (ItemType it : Objects.requireNonNull(response.body())) {
          System.out.println(it);
        }
      }
      @Override
      public void onFailure(Call<List<ItemType>> call, Throwable t) {
        System.out.println("FAILED RESPONSE"+ t.getMessage());
      }
    });
  }

  private static void generateAccessToken() {
    Retrofit.Builder builder = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    BlizzardAPI blizzardAPI = retrofit.create(BlizzardAPI.class);
    Call<AccessToken> call = blizzardAPI.getAccessToken(CLIENT_ID, CLIENT_SECRET, GRANT_TYPE);

    try {
      AccessToken accessToken = call.execute().body();
      assert accessToken != null;
      token = accessToken.getAccess_token();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
