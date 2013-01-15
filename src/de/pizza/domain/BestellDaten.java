package de.pizza.domain;

import java.util.Date;

public class BestellDaten {

  final Kunde kunde;
  final Date zeit;
  String inhalt = "";
  String lieferhinweis;

  BestellDaten(Kunde kunde) {
    this.zeit = Zeitgeber.getAktuelleZeit();
    this.kunde = kunde;
    this.lieferhinweis = kunde.getLieferhinweis();
  }

  public Date getZeit() {
    return zeit;
  }

  public String getZeitAsString() {
    return Bestellung.ZEIT_FORMAT.format(getZeit());
  }

  /*
   * Damit Nutzer dieser Klasse nichts über die Kunde-Klasse wissen müssen,
   * werden die Attribute, die im Zuge der Bestellungsbearbeitung benötigt
   * werden, weitergereicht
   * 
   * Vgl. The Law of Demeter
   */
  public String getKundenname() {
    return kunde.getName();
  }

  public String getKundenadresse() {
    return kunde.getAdresse();
  }

  public String getInhalt() {
    return inhalt;
  }

  public void setInhalt(String inhalt) {
    this.inhalt = inhalt;
  }

  public String getLieferhinweis() {
    return lieferhinweis;
  }

  public void setLieferhinweis(String lieferhinweis) {
    this.lieferhinweis = lieferhinweis;
  }

  public void bestellungAufnehmen() {
    kunde.bestellungAufnehmen(this);
  }

}
