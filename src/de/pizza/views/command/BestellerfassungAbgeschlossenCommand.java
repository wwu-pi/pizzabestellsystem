package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.BestellDaten;
import de.pizza.views.Bestelluebersicht;

public class BestellerfassungAbgeschlossenCommand implements Command {

  private BestellDaten bestellDaten;

  public BestellerfassungAbgeschlossenCommand(BestellDaten bestellDaten) {
    this.bestellDaten = bestellDaten;
  }

  @Override
  public void execute(Pizzeria pizzeria) {
    bestellDaten.bestellungAufnehmen();
  }

  @Override
  public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler) {
    return new Bestelluebersicht(pizzeria, commandHandler);
  }

}
