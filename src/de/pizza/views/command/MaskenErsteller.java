package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;

public interface MaskenErsteller {

  public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler);

}