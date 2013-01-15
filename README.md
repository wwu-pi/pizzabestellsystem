SE WS 2012/2013: Musterlösung für das Pizzabestellsystem
========================================================

Diese Musterlösung ist umfangreicher als zur Lösung der Aufgabe erforderlich war, um den praktischen Einsatz von verschiedenen Mustern zu veranschaulichen.

Die Main-Methode ist in <a href="pizzabestellsystem/blob/master/src/de/pizza/BestellsystemApplication.java">src/de/pizza/BestellsystemApplication.java</a> untergebracht.

Commands und Double Dispatch für die GUI
----------------------------------------
In dem Package <a href="pizzabestellsystem/tree/master/src/de/pizza/views/command">de.pizza.views.command</a> sind die Commands, die die Anwendung verwendet, abgelegt. Die Commands folgen dem Verhaltensmuster Befehl.

Zusätzlich wurde eine besondere Art des Besuchermusters eingesetzt: Double Dispatch. Durch den Einsatz von Double Dispatch werden die Masken erst durch Aufruf von dem GuiHandler erstellt. Ohne Double Dispatch würde bei jedem Aufruf von `execute` am Command die jeweilige Maske erstellt. Zum Testen ist das jedoch ungünstig, da das Initialisieren eines JFrames Zeit in Anspruch nimmt (Das Initialisieren eines einzelnen JFrame dauert in etwa solange wie das Ausführen aller Testfälle). Außerdem wird durch den Einsatz von Double Dispatch eine Trennung zwischen fachlichen und technischen Aspekten erzielt. 

Das folgende Sequenzdiagramm illustriert den Ablauf beim Aufruf eines Befehls, der einen Maskenwechsel bedingt: <a href="pizzabestellsystem/blob/master/src/de/pizza/views/command/BestellerfassungAbgeschlossenCommand.java">BestellerfassungAbgeschlossenCommand</a>.

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

Strategy für Zeitgeber
----------------------
Die <a href="pizzabestellsystem/blob/master/src/de/pizza/domain/Zeitgeber.java">Zeitgeber</a>-Klasse besitzt ein statisches Feld in dem die aktuelle Strategie hinterlegt ist. Das Interface der Strategy besteht lediglich aus der Methode `getZeit`.

Der Zeitgeber bietet die Möglichkeit per `setStrategy` eine beliebige `Strategy` für das Abrufen der aktuellen Zeit zu hinterlegen. Dies ist besonders beim Testen von Initialisierungen mit Datumswerten hilfreich; über eine für den Test definierte Strategie wird die aktuelle Zeit festgesetzt und kann dann von den zu testenden Objekten verwendet werden. Wichtig ist dabei, dass die temporäre Strategie anschließend durch die Ursprüngliche ersetzt wird (siehe `Zeitgeber.resetStrategy`).

Weil das Feld für die Strategie und die Methoden der Zeitgeber-Klasse statisch sind, wird hier implizit ein Singleton erstellt. Eine Garantie hierfür gibt es jedoch nicht, da unterschiedliche JVMs unterschiedliche ClassLoader einsetzen. Die ClassLoader gehen teilweise unterschiedlich mit statischen Feldern um.

Spy im Pizzeria Persistenz Test
-------------------------------
Im Test für die Persistenz der Pizzeria (<a href="pizzabestellsystem/blob/master/src/de/pizza/controller/PizzeriaPersistenzTest.java">PizzeriaPersistenzTest</a>) wird ein sogenannter <a href="http://xunitpatterns.com/Test%20Spy.html">Spy</a> eingesetzt. Mit Hilfe des Spy kann auf einfachste Art und Weise sichergestellt werden, dass die Methode `Pizzeria.persistiereKunden` die Methode `speichereKunden` am übergebenen `KundenSenke`-Objekt aufruft.

Alternativ hätte auch ein Mock genutzt werden können. Mocks werden im nächsten Abschnitt erläutert.

Mocking für Command-Tests
-------------------------
Damit während der Tests der Commands keine grafischen Oberflächen erzeugt werden, wurde der an die Befehle übergebene `GuiHandler` durch ein sogenanntes Mock-Objekt ersetzt. In diesem Fall wurden die Mock-Objekte mit Hilfe des Mocking-Frameworks <a href="https://code.google.com/p/mockito/">Mockito</a> erstellt.

Das Mock-Objekt stellt alle Methoden, die `GuiHandler` anbietet, bereit. Aufrufer erhalten eine Antwort auf ihre Anfragen, obwohl keine konkrete Implementierung vorliegt. Darüberhinaus können Mock-Objekte mit Antworten auf Anfragen ausgestattet werden (siehe `Mockito.when`).

In einem Testfall kann mit Hilfe der Methode `Mockito.verify` abschließend festgestellt werden, ob das Mock-Objekt den Erwartungen entsprechend aufgerufen wurde. Beispielsweise soll die Stornierung einer Bestellung keinen Maskenwechsel auslösen. Dies kann durch den Aufruf `Mockito.verifyZeroInteractions` sichergestellt werden (siehe <a href="pizzabestellsystem/blob/master/src/de/pizza/views/command/BestellungStornierenTest.java">BestellungStornierenTest</a>).

Testabdeckung
-------------
Die Anwendung hat eine recht gute Testabdeckung. Bislang nicht erfasst ist das Verhalten der grafischen Oberfläche und die Initialisierung der Anwendung.

![Testabdeckung am 15. Januar 2013 17:00](pizzabestellsystem/raw/master/testcoverage.png)
