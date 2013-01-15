package de.pizza.views.command;

import org.junit.Before;
import org.junit.Test;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Kunde;
import de.pizza.views.GuiHandler;

import static org.mockito.Mockito.*;

public class KundeBearbeitenTest {
  private Pizzeria pizzeria;
  private CommandHandler commandHandler;
  private GuiHandler guiHandler;
  
  @Before
  public void setUp() {
    pizzeria = mock(Pizzeria.class);
    commandHandler = mock(CommandHandler.class);
    guiHandler = mock(GuiHandler.class);
  }

  @Test
  public void sollteNichtVorhandenenKundenAnlegen() {
    String nichtVorhandeneTelefonnummer = "DUMMY";
    
    when(pizzeria.sucheKunde(nichtVorhandeneTelefonnummer)).thenReturn(null);
    when(pizzeria.erstelleKunde(nichtVorhandeneTelefonnummer)).thenReturn(new Kunde(nichtVorhandeneTelefonnummer));
    
    new KundeBearbeitenCommand(nichtVorhandeneTelefonnummer).execute(pizzeria, commandHandler, guiHandler);

    verify(pizzeria).sucheKunde(nichtVorhandeneTelefonnummer);
    verify(pizzeria).erstelleKunde(nichtVorhandeneTelefonnummer);
  }
  
  @Test
  public void sollteVorhandenenKundenVerwenden() {
    String vorhandeneTelefonnummer = "DUMMY";
    
    when(pizzeria.sucheKunde(vorhandeneTelefonnummer)).thenReturn(new Kunde(vorhandeneTelefonnummer));
    
    new KundeBearbeitenCommand(vorhandeneTelefonnummer).execute(pizzeria, commandHandler, guiHandler);
    
    verify(pizzeria).sucheKunde(vorhandeneTelefonnummer);
  }
  
  @Test
  public void sollteMaskenWechselAusloesen() {
    KundeBearbeitenCommand testObject = new KundeBearbeitenCommand("DUMMY");
    
    testObject.execute(pizzeria, commandHandler, guiHandler);
    
    verify(guiHandler).wechseleZuMaskeVon(testObject);
  }

}
