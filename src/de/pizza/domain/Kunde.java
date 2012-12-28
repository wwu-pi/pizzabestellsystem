package de.pizza.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Kunde implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String telefonnummer;
  private String name = "";
  private String adresse = "";
  private String lieferhinweis = "";
  private final Set<Bestellung> bestellungen = new HashSet<>();

  public Kunde(String telefonnummer) {
    this.telefonnummer = telefonnummer;
  }

  public String getTelefonnummer() {
    return telefonnummer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public String getLieferhinweis() {
    return lieferhinweis;
  }

  public void setLieferhinweis(String lieferhinweis) {
    this.lieferhinweis = lieferhinweis;
  }

  public Set<Bestellung> getBestellungen() {
    return Collections.unmodifiableSet(bestellungen);
  }

  public BestellDaten bestellerfassungStarten() {
    return new BestellDaten(this);
  }

  void bestellungAufnehmen(BestellDaten bestellDaten) {
    bestellungen.add(new Bestellung(bestellDaten));
  }

  void removeBestellung(Bestellung bestellung) {
    bestellungen.remove(bestellung);
  }

}
