package de.herrbert.pizza.domain;

import java.io.Serializable;
import java.util.Date;

public class Bestellung implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Kunde kunde;
	private final Date zeit;
	private String lieferhinweis;

	public Bestellung(Kunde kunde) {
		this.kunde = kunde;
		this.zeit = Zeitgeber.getAktuelleZeit();
		this.setLieferhinweis(kunde.getLieferhinweis());
	}

	public void stornieren() {
		kunde.removeBestellung(this);
	}

	public Date getZeit() {
		return zeit;
	}

	public String getLieferhinweis() {
		return lieferhinweis;
	}

	public void setLieferhinweis(String lieferhinweis) {
		this.lieferhinweis = lieferhinweis;
	}

}
