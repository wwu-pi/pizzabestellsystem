package de.herrbert.pizza.controller;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;

import de.herrbert.pizza.domain.Kunde;
import de.herrbert.pizza.domain.serializer.KundenDeserialisierer;

import static org.junit.Assert.*;

public class PizzeriaTest {

	private static final String TELEFONNUMMER = "";
	
	@Test
	public void sollteKundenSuchenKoennen() {
		Pizzeria pizzeria = neuePizzeriaMitEinemKunden();
		assertThat(pizzeria.sucheKunde(TELEFONNUMMER), is(not(nullValue())));
	}

	private Pizzeria neuePizzeriaMitEinemKunden() {
		return new Pizzeria(new KundenDeserialisierer() {
			@Override
			public Set<Kunde> deserialisiereKunden() {
				Set<Kunde> kunden = new HashSet<>();
				kunden.add(new Kunde(TELEFONNUMMER));
				return kunden;
			}
		});
	}
	
}
