package de.pizza.controller;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;

import de.pizza.domain.Kunde;
import de.pizza.domain.serializer.KundenQuelle;

import static org.junit.Assert.*;

public class PizzeriaTest {

  private static final String TELEFONNUMMER = "";

  @Test
  public void sollteKundenSuchenKoennen() {
    Pizzeria pizzeria = neuePizzeriaMitEinemKunden();
    assertThat(pizzeria.sucheKunde(TELEFONNUMMER), is(not(nullValue())));
  }
  
  private Pizzeria neuePizzeriaMitEinemKunden() {
    return new Pizzeria(new KundenQuelle() {
      @Override
      public Set<Kunde> ladeKunden() {
        Set<Kunde> kunden = new HashSet<>();
        kunden.add(new Kunde(TELEFONNUMMER));
        return kunden;
      }
    });
  }
  
  @Test
  public void sollteErstelltenKundenNichtDirektSpeichern() {
    Pizzeria pizzeria = neuePizzeriaMitEinemKunden();
    
    pizzeria.erstelleKunde("DUMMY");
    
    assertThat(pizzeria.sucheKunde("DUMMY"), is(nullValue()));
  }
  
  @Test
  public void sollteKundenDurchErsetzenSpeichern() {
    Pizzeria pizzeria = neuePizzeriaMitEinemKunden();
    
    Kunde kunde = pizzeria.erstelleKunde("DUMMY");
    pizzeria.ersetze(kunde);
    
    assertThat(pizzeria.sucheKunde("DUMMY"), is(kunde));
  }
  
  @Test
  public void sollteAlleBestellungenAusgebenKoennen() {
    Pizzeria pizzeria = neuePizzeriaMitEinemKunden();
     
    Kunde kunde = pizzeria.sucheKunde(TELEFONNUMMER);
    kunde.bestellerfassungStarten().bestellungAufnehmen();
    kunde.bestellerfassungStarten().bestellungAufnehmen();
    
    assertThat(pizzeria.getAlleBestellungen().size(), is(2));
  }

}
