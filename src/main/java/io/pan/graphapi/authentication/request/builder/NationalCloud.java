package io.pan.graphapi.authentication.request.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class NationalCloud implements Function<String, String> {

  private final Map<String, String> clouds;

  private NationalCloud(Map<String, String> clouds) {
    this.clouds = clouds;
  }

  public NationalCloud() {
    this(new HashMap<>());
    clouds.put("global", "https://login.microsoftonline.com/");
    clouds.put("germany", "https://login.microsoftonline.de/");
    clouds.put("usgovernment", "https://login.microsoftonline.us/");
    clouds.put("china", "https://login.chinacloudapi.cn/");
  }

  @Override
  public String apply(String nation) {
    return nation == null || nation.isEmpty()
        ? clouds.get("global")
        : cloudByNation(nation.toLowerCase());
  }

  private String cloudByNation(String nation) {
    if (!clouds.containsKey(nation)) {
      throw new IllegalArgumentException(
          String.format("Wrong national cloud provided. Allowed values: %s",
              String.join(", ", clouds.keySet()))
      );
    }
    return clouds.get(nation);
  }
}
