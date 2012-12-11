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

public class BestellsystemApplication {
	public static void main(String[] args) {
		final Pizzeria pizzeria = new Pizzeria(new KundenDeserialisierer() {
			@Override
			public Set<Kunde> deserialisiereKunden() {
				Set<Kunde> kunden = new HashSet<>();
				for (int i = 0; i < 50; i++) {
					Kunde kunde = new Kunde("1");
					kunde.bestellungAufnehmen();
					kunden.add(kunde);
				}
				return kunden;
			}
		});
		Bestelluebersicht bestelluebersicht = new Bestelluebersicht(pizzeria);
		bestelluebersicht.addWindowListener(new WindowAdapter() {
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
		});
	}
}
