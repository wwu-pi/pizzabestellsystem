package de.herrbert.pizza.views;

import javax.swing.JFrame;

import de.herrbert.pizza.domain.Bestellung;

public class Bestellerfassung extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Bestellung bestellung;

	public Bestellerfassung(Bestellung bestellung) {
		super("Bestellerfassung");
		this.bestellung = bestellung;
	}

	
}
