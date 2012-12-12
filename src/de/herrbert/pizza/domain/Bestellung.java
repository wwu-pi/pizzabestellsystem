package de.herrbert.pizza.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bestellung implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final SimpleDateFormat ZEIT_FORMAT = new SimpleDateFormat("dd.MM.yyyy / HH:mm:ss");

	private final Date zeit;
	private final Kunde kunde;
	private String inhalt = "";
	private String lieferhinweis;


	public Bestellung(Kunde kunde) {
		this.zeit = Zeitgeber.getAktuelleZeit();
		this.kunde = kunde;
		this.lieferhinweis = kunde.getLieferhinweis();
	}

	public void stornieren() {
		kunde.removeBestellung(this);
	}

	public Date getZeit() {
		return zeit;
	}
	
	public String getZeitAsString() {
		return ZEIT_FORMAT.format(getZeit());
	}

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

	@Override
	public String toString() {
		return ZEIT_FORMAT.format(getZeit()) + " / " + getKundenname();
	}

}
