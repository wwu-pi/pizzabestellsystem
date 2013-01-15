package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.BestellDaten;
import de.pizza.views.Bestelluebersicht;
import de.pizza.views.GuiHandler;

public class BestellerfassungAbgeschlossenCommand implements Command, MaskenErsteller {

  private BestellDaten bestellDaten;

  public BestellerfassungAbgeschlossenCommand(BestellDaten bestellDaten) {
    this.bestellDaten = bestellDaten;
  }

  @Override
  public void execute(Pizzeria pizzeria, CommandHandler commandHandler, GuiHandler guiHandler) {
    bestellDaten.bestellungAufnehmen();
    guiHandler.wechseleZuMaskeVon(this);
  }

  @Override
  public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler) {
    return new Bestelluebersicht(pizzeria, commandHandler);
  }

}
