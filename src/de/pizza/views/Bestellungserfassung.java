package de.pizza.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.pizza.domain.Bestellung;
import de.pizza.views.command.BestellerfassungAbgebrochenCommand;
import de.pizza.views.command.CommandHandler;

public class Bestellungserfassung extends Bestellungsbearbeitung {
	private static final long serialVersionUID = 1L;

	public Bestellungserfassung(Bestellung bestellung, CommandHandler commandHandler) {
		super(bestellung, commandHandler);
		customizeAbbrechenActionHandler();
	}

	private void customizeAbbrechenActionHandler() {
		for (ActionListener listener : abbrechen.getActionListeners()) {
			abbrechen.removeActionListener(listener);
		}
		abbrechen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				commandHandler.process(new BestellerfassungAbgebrochenCommand(bestellung));
			}
		});
	}
}
