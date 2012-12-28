package de.pizza.views.command;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Kunde;
import de.pizza.views.GuiHandler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BestellungStartenTest {

  @Test
  public void sollteBestellungNichtVorDemErfassenAnlegen() {
    Pizzeria pizzeria = mock(Pizzeria.class);
    CommandHandler commandHandler = mock(CommandHandler.class);
    GuiHandler guiHandler = mock(GuiHandler.class);
    Kunde kunde = new Kunde("");
    Command command = new NeueBestellungErfassenCommand(kunde);

    command.execute(pizzeria, commandHandler, guiHandler);

    assertThat(kunde.getBestellungen().isEmpty(), is(true));
  }

}
