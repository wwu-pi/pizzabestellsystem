package de.pizza.views;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.views.command.Command;
import de.pizza.views.command.CommandHandler;

public interface GuiHandler {

  public abstract void wechseleZu(JFrame neuerMaske);

  public abstract void wechseleZu(Command command, Pizzeria pizzeria, CommandHandler commandHandler);

}
