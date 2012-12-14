package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Bestellung;
import de.pizza.domain.Kunde;
import de.pizza.views.Bestellungserfassung;

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
		return new Bestellungserfassung(bestellung, commandListener);
	}

}
