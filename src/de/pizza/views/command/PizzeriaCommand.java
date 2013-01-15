package de.pizza.views.command;


import de.pizza.controller.Pizzeria;
import de.pizza.views.GuiHandler;

public interface PizzeriaCommand {

  void execute(Pizzeria pizzeria, GuiHandler guiHandler);

}
