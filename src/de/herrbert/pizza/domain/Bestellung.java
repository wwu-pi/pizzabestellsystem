package de.herrbert.pizza.domain;

import java.io.Serializable;

public class Bestellung implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Kunde kunde;

	public Bestellung(Kunde kunde) {
		this.kunde = kunde;
	}

	public void stornieren() {
		kunde.removeBestellung(this);
	}

}
