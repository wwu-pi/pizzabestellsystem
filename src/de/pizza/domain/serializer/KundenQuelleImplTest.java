package de.pizza.domain.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.junit.Test;

import de.pizza.domain.Kunde;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class KundenQuelleImplTest {

	@Test
	public void sollteKundenLadenKoennen() throws IOException {
		ObjectInput oi = bereiteInputMitZweiKundenVor();
		KundenQuelle quelle = new KundenQuelleImpl(oi);
		assertThat(quelle.ladeKunden().size(), is(2));
	}

	private ObjectInput bereiteInputMitZweiKundenVor() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutput oo =  new ObjectOutputStream(baos);
		
		for (String telefonnummer : new String[] { "2", "5" }) {
			oo.writeObject(erstelleKundeMitEinerBestellungUnd(telefonnummer));
		}
		
		byte[] kundeInByteArray = baos.toByteArray();
		
		ByteArrayInputStream bais = new ByteArrayInputStream(kundeInByteArray);
		ObjectInput oi = new ObjectInputStream(bais);
		return oi;
	}

	private Kunde erstelleKundeMitEinerBestellungUnd(String telefonnummer) {
		Kunde kundeMitEinerBestellung = new Kunde(telefonnummer);
		kundeMitEinerBestellung.bestellerfassungStarten().bestellungAufnehmen();
		return kundeMitEinerBestellung;
	}

}
