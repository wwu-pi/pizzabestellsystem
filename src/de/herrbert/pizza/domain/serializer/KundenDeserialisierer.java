package de.herrbert.pizza.domain.serializer;

import java.util.Set;

import de.herrbert.pizza.domain.Kunde;

public interface KundenDeserialisierer {

	public abstract Set<Kunde> deserialisiereKunden();

}