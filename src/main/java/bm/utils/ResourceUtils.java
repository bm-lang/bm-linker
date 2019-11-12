package bm.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceUtils {

  public static String getResourceAsString(String name) {
    try {
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      URL url = classloader.getResource(name);
      if (url == null) {
        throw new RuntimeException("Resource not found: " + name);
      }
      Path path = Paths.get(url.toURI());
      return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    }
    catch (URISyntaxException | IOException e) {
      throw new RuntimeException("Cannot load resource: " + name, e);
    }
  }

}
