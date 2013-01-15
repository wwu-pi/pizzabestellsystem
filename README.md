SE WS 2012/2013: Musterlösung für das Pizzabestellsystem
========================================================

Diese Musterlösung ist umfangreicher als zur Lösung der Aufgabe erforderlich war, um den praktischen Einsatz von verschiedenen Mustern zu veranschaulichen.

Die Main-Methode ist in <a href="pizzabestellsystem/blob/master/src/de/pizza/BestellsystemApplication.java">src/de/pizza/BestellsystemApplication.java</a> untergebracht.

Commands und Double Dispatch für die GUI
----------------------------------------
In dem Package <a href="pizzabestellsystem/tree/master/src/de/pizza/views/command">de.pizza.views.command</a> sind die Commands, die die Anwendung verwendet, abgelegt. Die Commands folgen dem Verhaltensmuster Befehl.

Zusätzlich wurde eine besondere Art des Besuchermusters eingesetzt: Double Dispatch. Durch den Einsatz von Double Dispatch werden die Masken erst durch Aufruf von dem GuiHandler erstellt. Ohne Double Dispatch würde bei jedem Aufruf von `execute` am Command die jeweilige Maske erstellt. Zum Testen ist das jedoch ungünstig, da das Initialisieren eines JFrames Zeit in Anspruch nimmt (Das Initialisieren eines einzelnen JFrame dauert in etwa solange wie das Ausführen aller Testfälle). Außerdem wird durch den Einsatz von Double Dispatch eine Trennung zwischen fachlichen und technischen Aspekten erzielt. 

Das folgende Sequenzdiagramm illustriert den Ablauf beim Aufruf eines Befehls, der einen Maskenwechsel bedingt: <a href="pizzabestellsystem/blob/master/src/de/pizza/views/command/BestellerfassungAbgeschlossenCommand.java">BestellerfassungAbgeschlossenCommand</a>.

```
  ch:CommandHandler  gh:GuiHandler       c:Command  bd:BestellDaten
          |                 |               |                 |
process(c)|                 |               |                 |
--------->-                 |               |                 |
          -  execute(gh)    |               |                 |
          --------------------------------->-                 |
          -                 |               -  bestellungAufnehmen()
          -                 |               ----------------->-
          -                 |  wechseleZuMaskeVon(c)          |
          -                 -<---------------                 |
          -                 -               -                 |
          -                 -  erstelleMaske(p, ch)           |
          -                 -------------->--                 |
          -                 -              --  <<create>> (p, ch)
          -                 -              -------------------------> bu:Bestelluebersicht
          -                 -  bu          --                 |               |
          -                 -<---------------                 |               |
          -                 -               -                 |               |
          -                 -  setVisible(true)               |               |
          -                 ------------------------------------------------->-
          -                 |               -                 |               |
          -                 |               |                 |               |
          |                 |               |                 |               |
```

Strategy für Zeitgeber
----------------------

Spy im PizzeriaPersistenzTest.java
----------------------------------

Mocking für Command-Tests
-------------------------
