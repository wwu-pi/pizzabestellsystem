package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.views.GuiHandler;
import de.pizza.views.Kundensuche;

public class KundenSucheCommand implements Command, MaskenErsteller {

  @Override
  public void execute(Pizzeria pizzeria, CommandHandler commandHandler, GuiHandler guiHandler) {
    guiHandler.wechseleZu(this, pizzeria, commandHandler);
  }

  @Override
  public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler) {
    return new Kundensuche(commandHandler);
  }

}
