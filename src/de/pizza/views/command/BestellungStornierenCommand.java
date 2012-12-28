package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Bestellung;
import de.pizza.views.GuiHandler;

public class BestellungStornierenCommand implements Command {

  private Bestellung bestellung;

  public BestellungStornierenCommand(Bestellung bestellung) {
    this.bestellung = bestellung;
  }

  @Override
  public void execute(Pizzeria pizzeria, CommandHandler commandHandler, GuiHandler guiHandler) {
    bestellung.stornieren();
  }

  @Override
  public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler) {
    throw new RuntimeException("Stornieren einer Bestellung erfordert keine neue Maske");
  }

}
