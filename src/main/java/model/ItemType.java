package model;

public class ItemType {
  private String id;
  private String name;
  private String path;

  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getPath() {
    return path;
  }

  @Override
  public String toString() {
    return "ItemType{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", path='" + path + '\'' +
        '}';
  }
}
