package de.herrbert.pizza.views.command;

import javax.swing.JFrame;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.domain.Kunde;
import de.herrbert.pizza.views.Kundenerfassung;

public class KundeBearbeitenCommand implements Command {

	private final String telefonnummer;

	public KundeBearbeitenCommand(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	@Override
	public JFrame erstelleMaske(Pizzeria pizzeria, CommandListener commandListener) {
		Kunde suchergebnis = pizzeria.sucheKunde(telefonnummer);
		if (suchergebnis == null) {
			suchergebnis = pizzeria.erstelleKunde(telefonnummer);
		}
		return new Kundenerfassung(suchergebnis, commandListener);
	}

}
