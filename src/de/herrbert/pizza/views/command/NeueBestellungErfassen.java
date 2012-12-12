package de.herrbert.pizza.views.command;

import javax.swing.JFrame;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.domain.Kunde;
import de.herrbert.pizza.views.Bestellerfassung;

public class NeueBestellungErfassen implements Command {

	private Kunde kunde;

	public NeueBestellungErfassen(Kunde kunde) {
		this.kunde = kunde;
	}

	@Override
	public JFrame erstelleMaske(Pizzeria pizzeria,
			CommandListener commandListener) {
		Bestellung bestellung = kunde.bestellungAufnehmen();
		return new Bestellerfassung(bestellung, commandListener);
	}

}
