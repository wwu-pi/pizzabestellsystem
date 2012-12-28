package de.pizza.views.command;

import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Kunde;

import static org.junit.Assert.*;

public class BestellungStartenTest {

  @Test
  public void sollteBestellungNichtVorDemErfassenAnlegen() {
    Pizzeria pizzeria = Mockito.mock(Pizzeria.class);
    Kunde kunde = new Kunde("");
    Command command = new NeueBestellungErfassen(kunde);

    command.execute(pizzeria);

    assertThat(kunde.getBestellungen().isEmpty(), is(true));
  }

  @Test
  public void sollteBestellungDtoAnlegen() {


  }

}
