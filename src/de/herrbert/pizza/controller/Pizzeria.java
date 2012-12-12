package de.herrbert.pizza.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.domain.BestellungZeitComparator;
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

	public void ersetze(Kunde kunde) {
		addToKunden(kunde);
	}
	
	public void persistiereKunden(KundenSenke kundenSenke) {
		kundenSenke.speichereKunden(new HashSet<>(kunden.values()));
	}

	public Kunde sucheKunde(String telefonnummer) {
		return kunden.get(telefonnummer);
	}

	public Kunde erstelleKunde(String telefonnummer) {
		return new Kunde(telefonnummer);
	}

	public List<Bestellung> getAlleBestellungen() {
		List<Bestellung> alleBestellungen = new ArrayList<>();
		for (Kunde kunde : kunden.values()) {
			alleBestellungen.addAll(kunde.getBestellungen());
		}
		Collections.sort(alleBestellungen, new BestellungZeitComparator());
		return alleBestellungen;
	}

}
