package de.pizza.views.command;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Kunde;
import de.pizza.views.GuiHandler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class NeueBestellungErfassenTest {

  @Test
  public void sollteBestellungNichtVorDemErfassenAnlegen() {
    Pizzeria pizzeria = mock(Pizzeria.class);
    GuiHandler guiHandler = mock(GuiHandler.class);
    Kunde kunde = new Kunde("");
    PizzeriaCommand command = new NeueBestellungErfassenCommand(kunde);

    command.execute(pizzeria, guiHandler);

    assertThat(kunde.getBestellungen().isEmpty(), is(true));
  }

}
