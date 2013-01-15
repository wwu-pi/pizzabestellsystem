package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Bestellung;
import de.pizza.views.Bestellungsbearbeitung;
import de.pizza.views.GuiHandler;


public class BestellungBearbeitenCommand implements Command, MaskenErsteller {

  private final Bestellung bestellung;

  public BestellungBearbeitenCommand(Bestellung bestellung) {
    this.bestellung = bestellung;
  }

  @Override
  public void execute(GuiHandler guiHandler) {
    guiHandler.wechseleZuMaskeVon(this);
  }

  @Override
  public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler) {
    return new Bestellungsbearbeitung(bestellung, commandHandler);
  }

}
