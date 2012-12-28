package de.pizza.domain.serializer;

import java.util.Set;

import de.pizza.domain.Kunde;

public interface KundenSenke {

  public abstract void speichereKunden(Set<Kunde> kunden);

}