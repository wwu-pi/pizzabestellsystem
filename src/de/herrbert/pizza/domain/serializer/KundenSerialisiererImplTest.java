package de.herrbert.pizza.domain.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.herrbert.pizza.domain.Kunde;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import static org.junit.Assert.*;

public class KundenSerialisiererImplTest {

	@Test
	public void sollteKundenSerialisierenKoennen() throws IOException {
		Set<Kunde> kunden = new HashSet<>();
		kunden.add(new Kunde("1"));
		kunden.add(new Kunde("2"));
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutput kundenOutput = new ObjectOutputStream(baos);
		
		// assertTrue(baos.toByteArray().length == 4)
		
		KundenSerialisierer serialisierer = new KundenSerialisiererImpl(kundenOutput);
		serialisierer.serialisiereKunden(kunden);
		
		assertThat(baos.toByteArray().length, is(not(4)));
	}
}
