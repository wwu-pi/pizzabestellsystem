package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Bestellung;
import de.pizza.views.Bestellungsbearbeitung;


public class BestellungBearbeitenCommand implements Command {

	private final Bestellung bestellung;

	public BestellungBearbeitenCommand(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	@Override
	public void execute(Pizzeria pizzeria) {
	}

	@Override
	public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler) {
		return new Bestellungsbearbeitung(bestellung, commandHandler);
	}

}
