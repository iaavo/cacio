package net.java.openjdk.awt.peer.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

class LibraryLoader {

  private static boolean loaded;

  static synchronized void loadLibs() {
    if (!loaded) {
      final String libName = System.mapLibraryName("cacio-web");
      System.err.println("loading library: /" + libName);
      final InputStream in = LibraryLoader.class.getResourceAsStream("/" + libName);
      final File outFile = new File(System.getProperty("java.io.tmpdir") + File.separator + libName);
      try {
        Files
          .copy(in, FileSystems.getDefault().getPath(outFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
      } catch (final IOException ex) {
        ex.printStackTrace();
        System.exit(-1);
      }
      System.load(outFile.getAbsolutePath());
      loaded = true;
    }
  }
}