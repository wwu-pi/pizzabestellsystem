package de.herrbert.pizza.domain.serializer;

import java.util.Set;

import de.herrbert.pizza.domain.Kunde;

public interface KundenSenke {

	public abstract void speichereKunden(Set<Kunde> kunden);

}