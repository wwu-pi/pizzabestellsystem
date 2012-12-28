package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Bestellung;
import de.pizza.views.Bestelluebersicht;

public class BestellerfassungAbgebrochenCommand implements Command {

	private Bestellung bestellung;

	public BestellerfassungAbgebrochenCommand(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	@Override
	public void execute(Pizzeria pizzeria) {
		bestellung.stornieren();
	}

	@Override
	public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandListener) {
		return new Bestelluebersicht(pizzeria, commandListener);
	}

}
