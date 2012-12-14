package de.pizza.domain.serializer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.util.HashSet;
import java.util.Set;

import de.pizza.domain.Kunde;

public class KundenQuelleImpl implements KundenQuelle {

	private final ObjectInput kundenInput;
	
	public KundenQuelleImpl(ObjectInput kundenInput) {
		this.kundenInput = kundenInput;
	}

	public Set<Kunde> ladeKunden() {
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
