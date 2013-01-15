package de.pizza.views.command;

import org.junit.Test;

import de.pizza.domain.Bestellung;
import de.pizza.views.GuiHandler;

import static org.mockito.Mockito.*;

public class BestellungStornierenTest {

  @Test
  public void sollteBestellungStornieren() {
    Bestellung bestellung = mock(Bestellung.class);
    
    new BestellungStornierenCommand(bestellung).execute(null);
    
    verify(bestellung).stornieren();
  }
  
  @Test
  public void sollteKeinenMaskenWechselAusloesen() {
    GuiHandler guiHandler = mock(GuiHandler.class);
    Bestellung bestellung = mock(Bestellung.class);
     
    new BestellungStornierenCommand(bestellung).execute(guiHandler);
    
    verifyZeroInteractions(guiHandler);
  }

}
