package de.herrbert.pizza.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.domain.Kunde;
import de.herrbert.pizza.domain.serializer.KundenQuelle;
import de.herrbert.pizza.domain.serializer.KundenSenke;

public class Pizzeria {

	private Map<String, Kunde> kunden = new HashMap<>();
	
	private KundenQuelle quelle;
	
	public Pizzeria(KundenQuelle quelle) {
		this.quelle = quelle;
		ladeKunden();
	}
	
	private void ladeKunden() {
		for (Kunde kunde : quelle.ladeKunden()) {
			addToKunden(kunde);
		}
	}
	
	private void addToKunden(Kunde neuerKunde) {
		kunden.put(neuerKunde.getTelefonnummer(), neuerKunde);
	}
	
	public void persistiereKunden(KundenSenke kundenSenke) {
		kundenSenke.speichereKunden(new HashSet<>(kunden.values()));
	}

	public Kunde sucheKunde(String telefonnummer) {
		return kunden.get(telefonnummer);
	}
	
	public Kunde erstelleKunde(String telefonnummer) {
		Kunde neuerKunde = new Kunde(telefonnummer);
		addToKunden(neuerKunde);
		return neuerKunde;
	}

	public Set<Bestellung> getAlleBestellungen() {
		Set<Bestellung> alleBestellungen = new HashSet<>();
		for (Kunde kunde : kunden.values()) {
			alleBestellungen.addAll(kunde.getBestellungen());
		}
		return alleBestellungen;
	}

}
