/*
 * Copyright 2006 by Heiner Jostkleigrewe
 * Diese Datei steht unter LGPL - siehe beigefügte lpgl.txt
 */
package de.jost_net.OBanToo.SEPA.Land;

public class IBANParam
{
  public enum Typ
  {
    NUMERIC, ALPHA, CHARACTER
  }

  private int len;

  private Typ typ;

  public IBANParam(int len, Typ typ)
  {
    this.len = len;
    this.typ = typ;
  }

  public int getLen()
  {
    return len;
  }

  public Typ getTyp()
  {
    return typ;
  }

}
