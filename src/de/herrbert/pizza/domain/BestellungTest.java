package de.herrbert.pizza.domain;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.herrbert.pizza.domain.Zeitgeber.Strategy;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class BestellungTest {

	private Date gesetzteZeit = new Date();

	@Before
	public void setUp() {
		Zeitgeber.setStrategy(new Strategy() {
			@Override
			public Date getZeit() {
				return gesetzteZeit;
			}
		});
	}
	
	@Test
	public void sollteAktuelleZeitVermerken() {
		Bestellung bestellung = new Kunde("").bestellungAufnehmen();
		assertThat(bestellung.getZeit(), is(gesetzteZeit ));
	}
	
	@Test
	public void sollteLieferhinweisVomKundenUebernehmen() {
		Kunde kunde = new Kunde("");
		kunde.setLieferhinweis("asdf");
		Bestellung bestellung = kunde.bestellungAufnehmen();
		assertThat(bestellung.getLieferhinweis(), is(kunde.getLieferhinweis()));
	}
	
	@After
	public void tearDown() {
		Zeitgeber.resetStrategy();
	}

}
