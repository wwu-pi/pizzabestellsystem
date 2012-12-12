package de.herrbert.pizza.views.command;

import javax.swing.JFrame;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.domain.Kunde;
import de.herrbert.pizza.views.Bestellerfassung;

public class NeueBestellungErfassen implements Command {

	private final Kunde kunde;

	private Bestellung bestellung;

	public NeueBestellungErfassen(Kunde kunde) {
		this.kunde = kunde;
	}

	@Override
	public void execute(Pizzeria pizzeria) {
		pizzeria.ersetze(kunde);
		bestellung = kunde.bestellungAufnehmen();
	}

	@Override
	public JFrame erstelleMaske(Pizzeria pizzeria, CommandListener commandListener) {
		return new Bestellerfassung(bestellung, commandListener);
	}

}
