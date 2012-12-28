package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.BestellDaten;
import de.pizza.domain.Kunde;
import de.pizza.views.Bestellungserfassung;
import de.pizza.views.GuiHandler;

public class NeueBestellungErfassen implements Command {

  private final Kunde kunde;

  private BestellDaten bestellDaten;

  public NeueBestellungErfassen(Kunde kunde) {
    this.kunde = kunde;
  }

  @Override
  public void execute(Pizzeria pizzeria, CommandHandler commandHandler, GuiHandler guiHandler) {
    pizzeria.ersetze(kunde);
    bestellDaten = kunde.bestellerfassungStarten();
    guiHandler.wechseleZu(new Bestellungserfassung(bestellDaten, commandHandler));
  }

  @Override
  public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler) {
    return new Bestellungserfassung(bestellDaten, commandHandler);
  }

}
