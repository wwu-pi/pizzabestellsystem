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
	private JFrame aktuelleMaske = new JFrame("empty");
	private Pizzeria pizzeria;

	public BestellsystemApplication(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}
	
	private void start() {
		wechseleZu(new BestellUebersicht(pizzeria, this));
	}
	
	private void wechseleZu(JFrame neuerMaske) {
		aktuelleMaske.setVisible(false);
		aktuelleMaske.dispose();
		
		aktuelleMaske = neuerMaske;
		
		aktuelleMaske.setLocationRelativeTo(null);
		aktuelleMaske.addWindowListener(new WindowClosingHandler(pizzeria));
		aktuelleMaske.setVisible(true);
	}

	@Override
	public void process(Command command) {
		wechseleZu(command.erstelleMaske(pizzeria, this));
	}
	
	public static void main(String[] args) {
		BestellsystemApplication app = new BestellsystemApplication(pizzeriaMitFuenfKunden());
		app.start();
	}
	
	private static Pizzeria pizzeriaMitFuenfKunden() {
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
