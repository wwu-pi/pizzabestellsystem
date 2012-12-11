package de.herrbert.pizza.domain.serializer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.util.HashSet;
import java.util.Set;

import de.herrbert.pizza.domain.Kunde;

public class KundenDeserialisiererImpl implements KundenDeserialisierer {

	private final ObjectInput kundenInput;
	
	public KundenDeserialisiererImpl(ObjectInput kundenInput) {
		this.kundenInput = kundenInput;
	}

	public Set<Kunde> deserialisiereKunden() {
		final Set<Kunde> kunden = new HashSet<>();
		try {
			while (true) {
				kunden.add((Kunde) kundenInput.readObject());
			}
		} catch (EOFException e) {
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return kunden;
	}

}
