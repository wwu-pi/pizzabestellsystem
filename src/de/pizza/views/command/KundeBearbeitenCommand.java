package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Kunde;
import de.pizza.views.Kundenerfassung;

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
	public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler) {
		return new Kundenerfassung(kunde, commandHandler);
	}

}
