/* EscherFontManager.java
   Copyright (C) 2008 Mario Torre and Roman Kennke

This file is part of the Caciocavallo project.

Caciocavallo is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Caciocavallo is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Caciocavallo; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

Linking this library statically or dynamically with other modules is
making a combined work based on this library.  Thus, the terms and
conditions of the GNU General Public License cover the whole
combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent
modules, and to copy and distribute the resulting executable under
terms of your choice, provided that you also meet, for each linked
independent module, the terms and conditions of the license of that
module.  An independent module is a module which is not derived from
or based on this library.  If you modify this library, you may extend
this exception to your version of the library, but you are not
obligated to do so.  If you do not wish to do so, delete this
exception statement from your version. */

package gnu.java.awt.peer.x;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;

import sun.awt.FontConfiguration;
import sun.font.FontManager;
import sun.font.PhysicalFont;
import sun.font.SunFontManager;

public class EscherFontManager
    extends SunFontManager
{  
    private PhysicalFont defaultPhysicalFont;

  @Override
  public String getFontPath(boolean noType1Fonts)
  {
    return "/usr/share/fonts/";
  }

  @Override
  protected FontConfiguration createFontConfiguration()
  {
    return new EscherFontConfiguration(this);
  }

  @Override
  public FontConfiguration createFontConfiguration(boolean preferLocaleFonts,
                                                   boolean preferPropFonts)
  {
    return new EscherFontConfiguration(this);
  }

  /**
   * Returns an array of two strings. The first element is the
   * name of the font. The second element is the file name.
   */
  @Override
  public String[] getDefaultPlatformFont() {
    // TODO: This probably only works on my Linux distro (Ubuntu). Maybe
    // figure out something more clever.
    return new String[] { "DejaVu Sans Serif",
                          "/usr/share/fonts/truetype/ttf-dejavu/DejaVuSans.ttf"
                        };
  }

  @Override
  public PhysicalFont getDefaultPhysicalFont() {
      if (defaultPhysicalFont == null) {
          File fontFile = new File(getDefaultPlatformFont()[1]);
          try {
              defaultPhysicalFont = (PhysicalFont)
              createFont2D(fontFile,
                      Font.TRUETYPE_FONT,
                      false);
          } catch (FontFormatException ex) {
              throw new Error("Probable fatal error:No fonts found.");
          }
      }
      return defaultPhysicalFont;
  }
}
