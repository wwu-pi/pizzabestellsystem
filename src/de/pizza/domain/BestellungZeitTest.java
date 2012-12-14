package de.pizza.domain;

import java.util.Comparator;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.pizza.domain.Zeitgeber.Strategy;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class BestellungZeitTest {

	private static Date REFERENZ_ZEIT = new Date();

	@Before
	public void setUp() {
		Zeitgeber.setStrategy(new Strategy() {
			@Override
			public Date getZeit() {
				return REFERENZ_ZEIT;
			}
		});
	}
	
	@Test
	public void sollteAktuelleZeitVermerken() {
		Bestellung bestellung = new Kunde("").bestellungAufnehmen();
		assertThat(bestellung.getZeit(), is(REFERENZ_ZEIT ));
	}

	@Test
	public void sollteBestellungAnhandIhrerZeitVergleichenKoennen() {
		Kunde kunde = new Kunde("");
		Bestellung bestellung = kunde.bestellungAufnehmen();

		Zeitgeber.setStrategy(new Strategy() {
			@Override
			public Date getZeit() {
				return new Date(REFERENZ_ZEIT.getTime() + 1);
			}
		});

		Bestellung neuereBestellung = kunde.bestellungAufnehmen();
	
		Comparator<Bestellung> comparator = new BestellungZeitComparator();
		assertThat(comparator.compare(bestellung, neuereBestellung), is(1));
	}

	@After
	public void tearDown() {
		Zeitgeber.resetStrategy();
	}

}
