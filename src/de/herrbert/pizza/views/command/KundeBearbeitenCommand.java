package de.herrbert.pizza.views.command;

import javax.swing.JFrame;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.domain.Kunde;
import de.herrbert.pizza.views.Kundenerfassung;

public class KundeBearbeitenCommand implements Command {

	private final String telefonnummer;

	private Kunde kunde;

	public KundeBearbeitenCommand(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	@Override
	public void execute(Pizzeria pizzeria) {
		kunde = pizzeria.sucheKunde(telefonnummer);
		if (kunde == null) {
			kunde = pizzeria.erstelleKunde(telefonnummer);
		}
	}

	@Override
	public JFrame erstelleMaske(Pizzeria pizzeria, CommandListener commandListener) {
		return new Kundenerfassung(kunde, commandListener);
	}

}
