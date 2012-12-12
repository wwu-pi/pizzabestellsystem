package de.herrbert.pizza.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.views.command.BestellerfassungAbgebrochenCommand;
import de.herrbert.pizza.views.command.CommandListener;

public class Bestellungserfassung extends Bestellungsbearbeitung {
	private static final long serialVersionUID = 1L;

	public Bestellungserfassung(Bestellung bestellung, CommandListener commandListener) {
		super(bestellung, commandListener);
		customizeAbbrechenActionHandler();
	}

	private void customizeAbbrechenActionHandler() {
		for (ActionListener listener : abbrechen.getActionListeners()) {
			abbrechen.removeActionListener(listener);
		}
		abbrechen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				commandListener.process(new BestellerfassungAbgebrochenCommand(bestellung));
			}
		});
	}
}
