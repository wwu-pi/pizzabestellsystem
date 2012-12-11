package de.herrbert.pizza.views;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.domain.Kunde;
import de.herrbert.pizza.domain.serializer.KundenDeserialisierer;
import de.herrbert.pizza.views.command.Command;
import de.herrbert.pizza.views.command.CommandListener;

public class BestellsystemApplication implements CommandListener {
	private JFrame aktuelleMaske;
	private Pizzeria pizzeria;

	public BestellsystemApplication(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}
	
	private void start() {
		aktuelleMaske = new BestellUebersicht(pizzeria, this);
		aktuelleMaske.addWindowListener(new WindowClosingHandler(pizzeria));
		aktuelleMaske.setVisible(true);
	}

	@Override
	public void process(Command command) {
		wechseleZu(command.erstelleMaske());
	}

	private void wechseleZu(JFrame neuerMaske) {
		aktuelleMaske.setVisible(false);
		aktuelleMaske = neuerMaske;
		aktuelleMaske.addWindowListener(new WindowClosingHandler(pizzeria));
		aktuelleMaske.setVisible(true);
	}
	
	public static void main(String[] args) {
		BestellsystemApplication app = new BestellsystemApplication(pizzeriaMit40Kunden());
		app.start();
	}
	
	private static Pizzeria pizzeriaMit40Kunden() {
		final Pizzeria pizzeria = new Pizzeria(new KundenDeserialisierer() {
			@Override
			public Set<Kunde> deserialisiereKunden() {
				Set<Kunde> kunden = new HashSet<>();
				for (int i = 0; i < 5; i++) {
					Kunde kunde = new Kunde("" + i);
					kunde.bestellungAufnehmen();
					kunden.add(kunde);
				}
				return kunden;
			}
		});
		return pizzeria;
	}
	
}
