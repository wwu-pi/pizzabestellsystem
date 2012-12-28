package de.pizza.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class KundeTest {

  private static final String DUMMY_NUMMER = "";
  private Kunde kunde;

  @Before
  public void setUp() {
    kunde = new Kunde(DUMMY_NUMMER);
  }

  @Test
  public void sollteBestellErfassungStarten() {
    assertThat(kunde.bestellerfassungStarten(), is(BestellDaten.class));
  }

  @Test
  public void sollteBestellungenVermerken() {
    kunde.bestellerfassungStarten().bestellungAufnehmen();
    assertThat(kunde.getBestellungen().size(), is(1));
  }

  @Test
  public void sollteBestellungStornierenKoennen() {
    kunde.bestellerfassungStarten().bestellungAufnehmen();
    Bestellung bestellung = kunde.getBestellungen().iterator().next();
    bestellung.stornieren();
    assertThat(kunde.getBestellungen().isEmpty(), is(true));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void sollteBestellungenNichtUeberOeffentlicheMethodenHinzufuegenKoennen() {
    kunde.getBestellungen().add(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void sollteBestellungenNichtUeberOeffentlicheMethodenLoeschenKoennen() {
    kunde.getBestellungen().clear();
  }

}
