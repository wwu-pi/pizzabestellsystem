package de.herrbert.pizza.views.command;

import javax.swing.JFrame;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.views.BestellUebersicht;

public class ZurUebersichtGehenCommand implements Command {

	@Override
	public JFrame erstelleMaske(Pizzeria pizzeria, CommandListener commandListener) {
		return new BestellUebersicht(pizzeria, commandListener);
	}

}
