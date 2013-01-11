package de.pizza.views.command;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import de.pizza.domain.BestellDaten;
import de.pizza.domain.Kunde;
import de.pizza.views.GuiHandler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BestellerfassungAbgeschlossenTest {
  
  @Test
  public void sollteBestellDatenAlsBestellungAufgeben() {
    CommandHandler commandHandler = mock(CommandHandler.class);
    GuiHandler guiHandler = mock(GuiHandler.class);
    Kunde kunde = new Kunde(""); 
    BestellDaten bestellDaten = kunde.bestellerfassungStarten();
    
    BestellerfassungAbgeschlossenCommand testObject = new BestellerfassungAbgeschlossenCommand(bestellDaten);
    testObject.execute(null, commandHandler, guiHandler);
    
    assertThat(kunde.getBestellungen().size(), is(1));
    verify(guiHandler).wechseleZu(testObject, null, commandHandler);
  }

}
