package net.java.openjdk.cacio.ctc;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class KeyStrokeMappingFactory {

  private static KeyStrokeMappingFactory instance = new KeyStrokeMappingFactory();

  private final Map<String, KeyStrokeMapping> maps = new HashMap<String, KeyStrokeMapping>();

  public static KeyStrokeMappingFactory getInstance() {
    return instance;
  }

  public KeyStrokeMapping getKeyStrokeMapping() {
    String lang = Locale.getDefault().getCountry().toLowerCase();
    KeyStrokeMapping mapping = this.maps.get(lang);
    if (mapping == null) {
      if (lang.equals("de")) {
        mapping = new KeyStrokeMappingDE();
      } else {
        mapping = new KeyStrokeMappingEN();
      }
      this.maps.put(lang, mapping);
    }
    return mapping;
  }
}
