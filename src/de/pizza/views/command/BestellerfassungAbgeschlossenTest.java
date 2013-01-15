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
    GuiHandler guiHandler = mock(GuiHandler.class);
    Kunde kunde = new Kunde(""); 
    BestellDaten bestellDaten = kunde.bestellerfassungStarten();
    
    BestellerfassungAbgeschlossenCommand testObject = new BestellerfassungAbgeschlossenCommand(bestellDaten);
    testObject.execute(null, guiHandler);
    
    assertThat(kunde.getBestellungen().size(), is(1));
    verify(guiHandler).wechseleZuMaskeVon(testObject);
  }

}
