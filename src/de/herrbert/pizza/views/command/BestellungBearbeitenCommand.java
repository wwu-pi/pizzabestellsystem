package de.herrbert.pizza.views.command;

import javax.swing.JFrame;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.views.Bestellungsbearbeitung;


public class BestellungBearbeitenCommand implements Command {

	private final Bestellung bestellung;

	public BestellungBearbeitenCommand(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	@Override
	public void execute(Pizzeria pizzeria) {
	}

	@Override
	public JFrame erstelleMaske(Pizzeria pizzeria, CommandListener commandListener) {
		return new Bestellungsbearbeitung(bestellung, commandListener);
	}

}
