package de.pizza.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class BestellungTest {
	
	private static final String ZEIT_FORMAT = "^\\d{2}[.]\\d{2}[.]\\d{4} / \\d{2}:\\d{2}:\\d{2}.*";

	private static final String KUNDEN_NAME = "Max Mustermann";
	private static final String ADRESSE = "Adresse";
	private static final String LIEFERHINWEIS = "Lieferhinweis";
	private static final String ABWEICHENDER_LIEFERHINWEIS = "Abweichender Lieferhinweis";

	private Kunde kunde;
	private Bestellung bestellung;

	@Before
	public void setUp() {
		kunde = new Kunde("");
		kunde.setName(KUNDEN_NAME);
		kunde.setAdresse(ADRESSE);
		kunde.setLieferhinweis(LIEFERHINWEIS);
		
		bestellung = new Bestellung(kunde.bestellerfassungStarten());
	}

	@Test
	public void sollteLieferhinweisVomKundenUebernehmen() {
		assertThat(bestellung.getLieferhinweis(), is(kunde.getLieferhinweis()));
	}

	@Test
	public void sollteLieferhinweisAmKundenUnveraendertLassen() {
		bestellung.setLieferhinweis(ABWEICHENDER_LIEFERHINWEIS);
		assertThat(kunde.getLieferhinweis(), is(LIEFERHINWEIS));
	}

	@Test
	public void sollteKundennameZurueckgebenKoennen() {
		assertThat(bestellung.getKundenname(), is(KUNDEN_NAME));
	}

	@Test
	public void sollteKundenadresseZurueckgebenKoennen() {
		assertThat(bestellung.getKundenadresse(), is(ADRESSE));
	}

	@Test
	public void sollteZeitAlsStringZurueckgebenKoennen() {
		assertThat(bestellung.getZeitAsString().matches(ZEIT_FORMAT), is(true));
	}

	@Test
	public void sollteDatumZeitUndKundennamePerToStringAnzeigenKoennen() {
		String bestellungAlsString = bestellung.toString();
    assertThat(bestellungAlsString.matches(ZEIT_FORMAT), is(true));
		assertThat(bestellungAlsString.endsWith(" / " + KUNDEN_NAME), is(true));
	}

}
