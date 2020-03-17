package net.java.openjdk.awt.peer.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class KeyStrokeMappingFactory {

  private static KeyStrokeMappingFactory instance = new KeyStrokeMappingFactory();

  private final Map<String, KeyStrokeMapping> maps = new HashMap<String, KeyStrokeMapping>();

  static KeyStrokeMappingFactory getInstance() {
    return instance;
  }

  KeyStrokeMapping getKeyStrokeMapping(String keyboard) {
    String lang = keyboard;
    if (lang == null) {
      lang = Locale.getDefault().getCountry().toLowerCase();
    }
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
