package de.herrbert.pizza.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import de.herrbert.pizza.domain.Kunde;
import de.herrbert.pizza.domain.serializer.KundenDeserialisierer;
import de.herrbert.pizza.domain.serializer.KundenSerialisierer;

public class Pizzeria {

	private Map<String, Kunde> kunden = new HashMap<>();
	
	private KundenDeserialisierer deserialisierer;
	
	public Pizzeria(KundenDeserialisierer deserialisierer) {
		this.deserialisierer = deserialisierer;
		ladeKunden();
	}
	
	private void ladeKunden() {
		for (Kunde kunde : deserialisierer.deserialisiereKunden()) {
			kunden.put(kunde.getTelefonnummer(), kunde);
		}
	}
	
	public void persistiereKunden(KundenSerialisierer kundenSerialisierer) {
		kundenSerialisierer.serialisiereKunden(new HashSet<>(kunden.values()));
	}

	public Kunde sucheKunde(String telefonnummer) {
		return kunden.get(telefonnummer);
	}

}
