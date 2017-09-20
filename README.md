Welkom bij de workshop die hoort bij de Java 9 Launch Party!

We hebben een project voor je klaarstaan dat nu nog in Java 8 draait.

Deze instructies nemen je stap voor stap mee om te migreren naar Java 9. 
Daarbij maak je kennis met enkele van de nieuwe features.

Mocht je vast komen te zitten, vraag dan één van de workshop-begeleiders om hulp.
Maar je kunt ook even spieken in de branch 'uitwerking_java9'.

_Let's get started!_

1. git clone deze repository
1. installeer [Maven3](https://maven.apache.org/download.cgi), mocht je dat nog niet ge&iuml;nstalleerd hebben
1. check dat je een Java 8 JDK actief hebt: 
	- `mvn -v` moet Java versie 1.8.0_x tonen
1. verifieer de installatie middels `mvn clean install -DskipTests` in een console geopend op de directory van je git clone
    - dit zal falen als er een andere JDK dan Java versie 8 actief is!
1. installeer [JDK9](http://jdk.java.net/9/) 
1. check de waarde van de environment variabelen `MAVEN_HOME` en `JAVA_HOME`.
	- `mvn -v` moet Java versie 9 tonen, en minimaal Maven 3.0.0 (liefst 3.3.x).
1. upgrade zo nodig je IDE:
	- [NetBeans (nightly!)](http://wiki.netbeans.org/JDK9Support) 
	- [Eclipse (Oxygen) met extra plugins](https://marketplace.eclipse.org/content/java-9-support-beta-oxygen)
	- IntelliJ: upgrade naar 2017.1.4 of hoger, mocht je dat nog niet gedaan hebben.
	    - Pas vanaf die versie wordt main() via het modulepath gestart. Je zult hier in stap 16 tegenaan lopen.
	    - Helaas worden _unit tests_ nog steeds via het classpath gestart
1. open het project - dat bestaat uit twee modules, 'user' en 'service' - in je favoriete Java IDE
1. pas in de POM's van user en service de `<source/>` en `<target/>` versies aan van 1.8 naar 9
1. upgrade de compiler-plugin in beide naar 3.7.0
	- versies 3.6.x ondersteunen al Java 9, maar falen op module-info.java files met commentaar..
1. maven-import de modules nu nogmaals in je IDE
	- controleer dat de syntax, JDK en compiler versies allemaal naar 9 zijn gezet door je IDE
	- Zo niet, corrigeer dit dan handmatig
1. hercompileer de code in je IDE
1. fix de error met de variabelenaam '_' door een zinnige naam te kiezen  
1. Draai `mvn clean install -DskipTests` om te verifiëren dat Maven naar Java 9 compileert
	- De enforcer plugin zou nu moeten falen. 
	- Fix dit door in de pom.xml bij `<requireJavaVersion/>` '1.8.1' te _vervangen_ door '9'.
	- Nu zou de enforcer opnieuw moeten falen, dit keer omdat artifact 'service' depend op 'user', en die nu naar java 9 compileert.
	- Fix dit door `<maxJdkVersion/>` naar 9 op te hogen
	- Compileer je code nogmaals om te zien of dit heeft geholpen; vanaf dit punt maakt het niet meer uit of je in je IDE of vanaf de command line compileert.
1. Nu is het tijd om ons project te modulariseren!
	- hernoem beide 'module-info.java9' files naar 'module-info.java'
	- je zult zien dat de compilatie opeens faalt met o.a. `package org.slf4j is not visible`.
	- Reden is dat slf4-api (een transitive dependency van logback-classic) wel als maven dependency bekend is - en dus op het classpath staat - maar dat we niet aan de compiler vertellen dat we dit als module willen gebruiken.
		- NB In gemodulariseerde projecten moeten _alle_ geimporteerde packages op het modulepath staan!
	- voeg in beide 'module-info.java' files toe: `requires slf4j.api;`
		- NB slf4j-api is nog niet gemodulariseerd! We maken hier gebruik van de enigszins controversi&euml;le 'automodule' feature. De maven build (dus vanaf command line) zal dan ook een grote waarschuwing tonen:
	
    [WARNING] ********************************************************************************************************************
    [WARNING] * Required filename-based automodules detected. Please don't publish this project to a public artifact repository! *
    [WARNING] ********************************************************************************************************************

1. class BusinessLayer faalt op een vergelijkbare error `package org.springframework.context.support is not visible`. 
	- Dit lossen we op door `requires spring.context;` toe te voegen aan de module-info.java van het 'user' project.
		- Mocht je je afvragen hoe we komen aan de (auto)modulenamen slf4j.api en spring.context: die worden afgeleid van de filenamen van de JARs op het classpath, resp. slf4j-api-1.7.5.jar en spring-context-4.3.9.RELEASE.jar. Documentatie van de 'automodule' magie zit verstopt in [de javadoc van ModuleFinder](http://download.java.net/java/jigsaw/docs/api/java/lang/module/ModuleFinder.html#of-java.nio.file.Path...-)
	- start nu `BusinessLayer.main()` vanuit je IDE.
	- je krijgt een `NoClassDefFoundError: java/sql/SQLException`.
	- Dat is vreemd! Die class zit toch gewoon in Java SE en is toch altijd in scope?
	- Nou: in Java 9 niet meer! Deze class is ingedeeld in module [java.sql](http://download.java.net/java/jdk9/docs/api/java.sql-summary.html). Alleen `java.base` zit automatisch in scope (net zoals `java.lang.*` classes altijd impliciet ge&iuml;mporteerd zijn).
	    - NB als je dezelfde code raakt via BusinessLayerTest krijg je deze fout niet. Waarom? Omdat [de surefire plugin nog helemaal niet met het modulepath kan omgaan](https://issues.apache.org/jira/browse/SUREFIRE-1262). Alle dependencies - dus ook Spring - worden op het classpath gezet, waardoor de encapsulatie die jigsaw biedt, niet aangeroepen wordt.
	- Dit probleem lossen we op d.m.v. toevoegen van `requires java.sql;` aan de module-info.java.
	- controleer of `BusinessLayer.main()` nu wel slaagt.
	- Helaas.. Een `IllegalAccessException [..] module service does not export nl.ordina.jtech.java9.service.collections.impl.internal`.
	- De Spring configuratie verwijst naar SuperCollectionServiceArraysAsListInternal, echter willen we die (om fictieve architecturele redenen ;-) niet exporteren uit onze service module (vandaar ook de 'impl.internal' package).
	- We willen echter wel (om dezelfde fictieve redenen) Spring deze implementatieklasse laten instanti&euml;ren via reflectie. 
	- Voeg om dat mogelijk te maken het volgende toe aan de module-info.java van de service module: `opens nl.ordina.jtech.java9.service.collections.impl.internal;`.
	- controleer of `BusinessLayer.main()` nu eindelijk groen wordt.
	- Als bonus kun je evt. de commented line in de constructor van BusinessLayer aanzetten, om te verifi&euml;ren dat de compiler je _echt_ geen toegang geeft, maar Spring wel.
1. Hiermee is de modularisatie van het workshop project voltooid en kun je verder gaan met het exploreren van enkele nieuwe syntax & JDK features van Java 9. Aan de slag! 
1. run alle unit tests in de service en user projecten; je ziet dat ze allen falen.
	- aan jou nu de taak om ze allemaal 'groen' te krijgen!
	- lees de opdracht per unit test, en voer deze uit.
	
Enkele links met achtergrondinfo die van pas zou kunnen komen:

- [Maven & Java9](https://cwiki.apache.org/confluence/display/MAVEN/Java+9+-+Jigsaw)
- [compiler-plugin & modules](https://maven.apache.org/plugins/maven-compiler-plugin/examples/module-info.html)
- [JDK9 Outreach](https://wiki.openjdk.java.net/display/Adoption/JDK+9+Outreach)
- [OpenJDK9](http://openjdk.java.net/projects/jdk9)
- [JDK9 javadoc](http://download.java.net/java/jdk9/docs/api/overview-summary.html)
- [Oracle what's new](https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-C23AFD78-C777-460B-8ACE-58BE5EA681F6)

_That's it!_

We hopen dat je veel plezier hebt beleefd aan deze workshop, en dat je er wat van opgestoken hebt. 

Vragen, opmerkingen? [Mail ons!](mailto:jtech@ordina.nl)
