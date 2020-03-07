package model;

public class AccessToken {
  private String access_token;
  private String token_type;
  private int expires_in;
  private String scope;

  public String getAccess_token() {
    return access_token;
  }

  public String getToken_type() {
    return token_type;
  }

  public int getExpires_in() {
    return expires_in;
  }

  public String getScope() {
    return scope;
  }
}
