package de.herrbert.pizza.controller;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.herrbert.pizza.domain.Kunde;
import de.herrbert.pizza.domain.serializer.KundenDeserialisierer;
import de.herrbert.pizza.domain.serializer.KundenSerialisierer;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class PizzeriaPersistenzTest {

	@Test
	public void sollteLadenUndSpeichernKoennen() {
		Pizzeria pizzeria = neuePizzeriaMitEinemKunden();
		SerialisiererSpy kundenSerialisierer = new SerialisiererSpy();
		
		pizzeria.persistiereKunden(kundenSerialisierer);
		
		assertThat(kundenSerialisierer.wurdeAufgerufen, is(true));
	}
	
	private Pizzeria neuePizzeriaMitEinemKunden() { 
		return new Pizzeria(new KundenDeserialisierer() {
			@Override
			public Set<Kunde> deserialisiereKunden() {
				Set<Kunde> kunden = new HashSet<>();
				kunden.add(new Kunde(""));
				return kunden;
			}
		});
	}
	
	class SerialisiererSpy implements KundenSerialisierer {
		boolean wurdeAufgerufen = false;

		@Override
		public void serialisiereKunden(Set<Kunde> kunden) {
			wurdeAufgerufen = true;
		}
	}

}
