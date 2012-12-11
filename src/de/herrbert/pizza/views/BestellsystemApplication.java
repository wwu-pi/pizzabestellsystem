package de.herrbert.pizza.views;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.domain.Kunde;
import de.herrbert.pizza.domain.serializer.KundenDeserialisierer;
import de.herrbert.pizza.domain.serializer.KundenSerialisiererImpl;
import de.herrbert.pizza.views.command.Command;
import de.herrbert.pizza.views.command.CommandListener;

public class BestellsystemApplication implements CommandListener {
	private static final class WindowClosingHandler extends WindowAdapter {
		private final Pizzeria pizzeria;

		private WindowClosingHandler(Pizzeria pizzeria) {
			this.pizzeria = pizzeria;
		}

		@Override
		public void windowClosing(WindowEvent e) {
			try {
				pizzeria.persistiereKunden(new KundenSerialisiererImpl(new ObjectOutputStream(new FileOutputStream("kunden.dat"))));
				System.exit(0);
			} catch (IOException exception) {
				exception.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {
		CommandListener app = new BestellsystemApplication();
		final Pizzeria pizzeria = pizzeriaMit40Kunden();
		BestellUebersicht bestelluebersicht = new BestellUebersicht(pizzeria, app);
		bestelluebersicht.addWindowListener(new WindowClosingHandler(pizzeria));
	}

	private static Pizzeria pizzeriaMit40Kunden() {
		final Pizzeria pizzeria = new Pizzeria(new KundenDeserialisierer() {
			@Override
			public Set<Kunde> deserialisiereKunden() {
				Set<Kunde> kunden = new HashSet<>();
				for (int i = 0; i < 50; i++) {
					Kunde kunde = new Kunde("" + i);
					kunde.bestellungAufnehmen();
					kunden.add(kunde);
				}
				return kunden;
			}
		});
		return pizzeria;
	}

	@Override
	public void process(Command bestellungBearbeitenCommand) {
	}
}
