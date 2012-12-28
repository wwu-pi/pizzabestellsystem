package de.pizza.domain;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.pizza.domain.Zeitgeber.Strategy;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class BestellDatenZeitTest {

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
    BestellDaten bestellDaten = new Kunde("").bestellerfassungStarten();
    assertThat(bestellDaten.getZeit(), is(REFERENZ_ZEIT ));
  }

  @After
  public void tearDown() {
    Zeitgeber.resetStrategy();
  }

}
