package de.pizza.controller;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.pizza.domain.Kunde;
import de.pizza.domain.serializer.KundenQuelle;
import de.pizza.domain.serializer.KundenSenke;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class PizzeriaPersistenzTest {

  @Test
  public void sollteLadenUndSpeichernKoennen() {
    Pizzeria pizzeria = neuePizzeriaMitEinemKunden();
    SenkeSpy kundenSenke = new SenkeSpy();

    pizzeria.persistiereKunden(kundenSenke);

    assertThat(kundenSenke.wurdeAufgerufen, is(true));
  }

  private Pizzeria neuePizzeriaMitEinemKunden() {
    return new Pizzeria(new KundenQuelle() {
      @Override
      public Set<Kunde> ladeKunden() {
        Set<Kunde> kunden = new HashSet<>();
        kunden.add(new Kunde(""));
        return kunden;
      }
    });
  }

  class SenkeSpy implements KundenSenke {
    boolean wurdeAufgerufen = false;

    @Override
    public void speichereKunden(Set<Kunde> kunden) {
      wurdeAufgerufen = true;
    }
  }

}
