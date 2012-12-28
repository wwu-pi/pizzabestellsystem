package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.views.Bestelluebersicht;

public class ZurUebersichtGehenCommand implements Command {

  @Override
  public void execute(Pizzeria pizzeria) {
  }

  @Override
  public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler) {
    return new Bestelluebersicht(pizzeria, commandHandler);
  }

}
