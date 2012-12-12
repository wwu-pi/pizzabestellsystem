package de.herrbert.pizza.domain;

import java.util.Comparator;

public final class BestellungZeitComparator implements Comparator<Bestellung> {
	@Override
	public int compare(Bestellung o1, Bestellung o2) {
		return o2.getZeit().compareTo(o1.getZeit());
	}

}
