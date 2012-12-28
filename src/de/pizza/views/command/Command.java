package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.views.GuiHandler;

public interface Command {

  void execute(Pizzeria pizzeria, CommandHandler commandHandler, GuiHandler guiHandler);

  JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler);

}
