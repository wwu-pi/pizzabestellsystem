package de.pizza.domain.serializer;

import java.util.Set;

import de.pizza.domain.Kunde;

public interface KundenQuelle {

  public abstract Set<Kunde> ladeKunden();

}