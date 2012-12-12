package de.herrbert.pizza.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import static org.junit.Assert.*;

public class BestellungTest {
	
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
		
		bestellung = kunde.bestellungAufnehmen();
	}
	
	@Test
	public void sollteLieferhinweisVomKundenUebernehmen() {
		assertThat(bestellung.getLieferhinweis(), is(kunde.getLieferhinweis()));
	}
	
	@Test
	public void sollteLieferhinweisAmKundenUnveraendertLassen() {
		bestellung.setLieferhinweis(ABWEICHENDER_LIEFERHINWEIS);
		assertThat(bestellung.getLieferhinweis(), is(not(kunde.getLieferhinweis())));
	}
	
	@Test
	public void sollteKundennameZurueckgebenKoennen() {
		assertThat(bestellung.getKundenname(), is(kunde.getName()));
	}
	
	@Test
	public void sollteKundenadresseZurueckgebenKoennen() {
		assertThat(bestellung.getKundenadresse(), is(kunde.getAdresse()));
	}

	@Test
	public void sollteDatumZeitUndKundennamePerToStringAnzeigenKoennen() {
		String format = "^\\d{2}[.]\\d{2}[.]\\d{4} / \\d{2}:\\d{2}:\\d{2} / .*";
		assertThat(bestellung.toString().matches(format), is(true));
		assertThat(bestellung.toString().endsWith(KUNDEN_NAME), is(true));
	}

}
