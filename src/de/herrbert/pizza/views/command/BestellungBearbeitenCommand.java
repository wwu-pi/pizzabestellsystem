package de.herrbert.pizza.views.command;

import javax.swing.JFrame;

import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.views.Bestellerfassung;


public class BestellungBearbeitenCommand implements Command {

	private Bestellung bestellung;
	private Bestellerfassung maske;

	public BestellungBearbeitenCommand(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	@Override
	public JFrame erstelleMaske() {
		maske = new Bestellerfassung(bestellung);
		return maske;
	}

}
