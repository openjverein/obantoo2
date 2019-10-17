/*
 * Copyright 2006 by Heiner Jostkleigrewe
 * Diese Datei steht unter LGPL - siehe beigefügte lpgl.txt
 */
package de.jost_net.OBanToo.Dtaus;

import de.jost_net.OBanToo.Tools.Tr;
import de.jost_net.OBanToo.Tools.Util;

public class Satz
{

  public Satz()
  {
    //
  }

  protected String makeValid(String value)
  {
    value = Tr.normalizeUTF8(value);
    value = value.replaceAll("a", "A");
    value = value.replaceAll("b", "B");
    value = value.replaceAll("c", "C");
    value = value.replaceAll("d", "D");
    value = value.replaceAll("e", "E");
    value = value.replaceAll("f", "F");
    value = value.replaceAll("g", "G");
    value = value.replaceAll("h", "H");
    value = value.replaceAll("i", "I");
    value = value.replaceAll("j", "J");
    value = value.replaceAll("k", "K");
    value = value.replaceAll("l", "L");
    value = value.replaceAll("m", "M");
    value = value.replaceAll("n", "N");
    value = value.replaceAll("o", "O");
    value = value.replaceAll("p", "P");
    value = value.replaceAll("q", "Q");
    value = value.replaceAll("r", "R");
    value = value.replaceAll("s", "S");
    value = value.replaceAll("t", "T");
    value = value.replaceAll("u", "U");
    value = value.replaceAll("v", "V");
    value = value.replaceAll("w", "W");
    value = value.replaceAll("x", "X");
    value = value.replaceAll("y", "Y");
    value = value.replaceAll("z", "Z");
    value = value.replaceAll("ä", "Ä");
    value = value.replaceAll("ö", "Ö");
    value = value.replaceAll("ü", "Ü");
    value = value.replaceAll("'", " ");
    return value;
  }

  protected void validCharacters(String value) throws DtausException
  {
    for (int i = 0; i < value.length(); i++)
    {
      char c = value.charAt(i);
      if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || c == ' '
          || c == '.' || c == ',' || c == '&' || c == '-' || c == '+'
          || c == '*' || c == '%' || c == '/' || c == '$' || c == 'Ä'
          || c == 'Ö' || c == 'Ü' || c == 'ß')
      {
        // gültig
      }
      else
      {
        throw new DtausException(DtausException.UNGUELTIGES_ZEICHEN,
            value.substring(i, i + 1) + "("
                + Util.toHex(value.substring(i, i + 1)) + ")" + " an Position "
                + i + ": " + value);
      }
    }
  }

  private String codingToDtaus(String value)
  {
    String ret = value;
    ret = ret.replace('Ä', (char) 0x5b);
    ret = ret.replace('ä', (char) 0x5b);
    ret = ret.replace('Ö', (char) 0x5c);
    ret = ret.replace('ö', (char) 0x5c);
    ret = ret.replace('Ü', (char) 0x5d);
    ret = ret.replace('ü', (char) 0x5d);
    ret = ret.replace('ß', (char) 0x7e);
    return ret;
  }

  protected String codingFromDtaus(String value, int toleranz)
  {
    String ret = value;
    ret = ret.replace((char) 0x5b, 'Ä');
    ret = ret.replace((char) 0x8e, 'Ä');
    ret = ret.replace((char) 0x5c, 'Ö');
    ret = ret.replace((char) 0x99, 'Ö');
    ret = ret.replace((char) 0x5d, 'Ü');
    ret = ret.replace((char) 0x9a, 'Ü');
    ret = ret.replace((char) 0x7e, 'ß');
    ret = ret.replace((char) 0xe1, 'ß');
    if (toleranz == DtausDateiParser.UMLAUTUMSETZUNG
        || toleranz == DtausDateiParser.HEX00TOSPACE || (toleranz
            & DtausDateiParser.UMLAUTUMSETZUNG) == DtausDateiParser.UMLAUTUMSETZUNG)
    {
      ret = ret.replace((char) 0x84, 'Ä');
      ret = ret.replace((char) 0x94, 'Ö');
      ret = ret.replace((char) 0x81, 'Ü');
    }
    if (toleranz == DtausDateiParser.HEX00TOSPACE || (toleranz
        & DtausDateiParser.HEX00TOSPACE) == DtausDateiParser.HEX00TOSPACE)
    {
      ret = ret.replace((char) 0x00, ' ');
    }
    return ret;
  }

  /**
   * Datenfelder auf die Länge 27 bringen
   */
  public String make27(String in)
  {
    String out = "";
    if (in.length() >= 27)
    {
      out = in.substring(0, 27);
    }
    if (in.length() < 27)
    {
      out = in + Tool.space(27 - in.length());
    }
    out = codingToDtaus(out);
    return out;
  }

}
