package de.herrbert.pizza.domain;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.herrbert.pizza.domain.Zeitgeber.Strategy;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class BestellungZeitTest {

	private static final Date REFERENZ_ZEIT = new Date();

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
	}
	
	@After
	public void tearDown() {
		Zeitgeber.resetStrategy();
	}

}
