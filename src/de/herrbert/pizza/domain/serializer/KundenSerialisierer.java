package de.herrbert.pizza.domain.serializer;

import java.util.Set;

import de.herrbert.pizza.domain.Kunde;

public interface KundenSerialisierer {

	public abstract void serialisiereKunden(Set<Kunde> kunden);

}