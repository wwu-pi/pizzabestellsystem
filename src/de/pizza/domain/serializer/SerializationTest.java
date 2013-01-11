package de.pizza.domain.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import de.pizza.domain.Kunde;

import static org.junit.Assert.*;

/**
 * Dieser Test hat mir beim Verständnis der Klasen {@link ObjectOutputStream}
 * und {@link ObjectInputStream} geholfen. Insbesondere die Serialisierung von
 * abhängigen Objekten (in diesem Fall die Bestellungen) war mir wichtig.
 * 
 * @author jan
 * 
 */
public class SerializationTest {

  private static final String DUMMY_NUMMER = "";

  @Test
  public void sollteKundenInklusiveBestellungenSerialisierenKoennen() throws IOException, ClassNotFoundException {
    Kunde kundeMitEinerBestellung = new Kunde(DUMMY_NUMMER);
    kundeMitEinerBestellung.bestellerfassungStarten().bestellungAufnehmen();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos =  new ObjectOutputStream(baos);
    oos.writeObject(kundeMitEinerBestellung);

    byte[] kundeInByteArray = baos.toByteArray();

    ByteArrayInputStream bais = new ByteArrayInputStream(kundeInByteArray);
    ObjectInputStream ois = new ObjectInputStream(bais);
    Kunde kundeVomOis = (Kunde) ois.readObject();
    assertThat(kundeVomOis.getBestellungen().size(),
        is(kundeMitEinerBestellung.getBestellungen().size()));
  }

}
