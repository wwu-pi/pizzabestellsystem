package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;

public interface Command {

	void execute(Pizzeria pizzeria);

	JFrame erstelleMaske(Pizzeria pizzeria, CommandListener commandListener);

}
