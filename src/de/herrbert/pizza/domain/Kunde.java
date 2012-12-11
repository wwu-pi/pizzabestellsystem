package de.herrbert.pizza.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Kunde implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String telefonnummer;
	private String lieferhinweis = "";
	
	private final Set<Bestellung> bestellungen = new HashSet<>();

	public Kunde(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
	
	public String getTelefonnummer() {
		return telefonnummer;
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
	
	public Bestellung bestellungAufnehmen() {
		Bestellung neueBestellung = new Bestellung(this);
		bestellungen.add(neueBestellung);
		return neueBestellung;
	}

	void removeBestellung(Bestellung bestellung) {
		bestellungen.remove(bestellung);
	}


}
