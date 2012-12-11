package de.herrbert.pizza.domain.serializer;

import java.io.IOException;
import java.io.ObjectOutput;
import java.util.Set;

import de.herrbert.pizza.domain.Kunde;

public class KundenSerialisiererImpl implements KundenSerialisierer {

	private ObjectOutput kundenOutput;

	public KundenSerialisiererImpl(ObjectOutput kundenOutput) {
		this.kundenOutput = kundenOutput;
	}

	@Override
	public void serialisiereKunden(Set<Kunde> kunden) {
		try {
			for (Kunde kunde : kunden) {
				kundenOutput.writeObject(kunde);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
