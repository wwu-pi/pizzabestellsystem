package de.herrbert.pizza.views.command;

import javax.swing.JFrame;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.views.BestellUebersicht;

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
	public JFrame erstelleMaske(Pizzeria pizzeria, CommandListener commandListener) {
		return new BestellUebersicht(pizzeria, commandListener);
	}

}
