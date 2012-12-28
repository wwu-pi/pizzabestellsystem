package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.views.Kundensuche;

public class KundenSucheCommand implements Command {

	@Override
	public void execute(Pizzeria pizzeria) {
	}

	@Override
	public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandListener) {
		return new Kundensuche(commandListener);
	}

}
