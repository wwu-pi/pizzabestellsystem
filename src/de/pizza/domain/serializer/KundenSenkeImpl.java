package de.pizza.domain.serializer;

import java.io.IOException;
import java.io.ObjectOutput;
import java.util.Set;

import de.pizza.domain.Kunde;

public class KundenSenkeImpl implements KundenSenke {

  private ObjectOutput kundenOutput;

  public KundenSenkeImpl(ObjectOutput kundenOutput) {
    this.kundenOutput = kundenOutput;
  }

  @Override
  public void speichereKunden(Set<Kunde> kunden) {
    try {
      for (Kunde kunde : kunden) {
        kundenOutput.writeObject(kunde);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
