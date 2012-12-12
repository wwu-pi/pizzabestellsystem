package de.herrbert.pizza.views.command;

import javax.swing.JFrame;

import de.herrbert.pizza.controller.Pizzeria;

public interface Command {

	void execute(Pizzeria pizzeria);

	JFrame erstelleMaske(Pizzeria pizzeria, CommandListener commandListener);

}
