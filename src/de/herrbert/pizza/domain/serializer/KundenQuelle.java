package de.herrbert.pizza.domain.serializer;

import java.util.Set;

import de.herrbert.pizza.domain.Kunde;

public interface KundenQuelle {

	public abstract Set<Kunde> ladeKunden();

}