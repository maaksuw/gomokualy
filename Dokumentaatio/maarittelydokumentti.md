# Määrittelydokumentti

## Toteutettavat algoritmit ja tietorakenteet
* Minimax-algoritmi
    * Tällä algoritmilla toteutetaan pelin tekoäly
* Alfa-beeta-karsinta
    * Alfa-beeta-karsinnalla yritetään karsia pelipuuta niin, että turhaa laskentaa tehtäisiin mahdollisimman vähän
* Taulukkolista yksinkertaisilla operaatioilla
    * Taulukkolistaa käytetään ohjelmassa siirtojen listaamiseen ja hajautustaulun sisäisessä toteutuksessa
* Hajautustaulu yksinkertaisilla opetaatioilla
    * Hajautustaulua käytetään ohjelmassa pelipuun solmujen arvojen taulukoimiseen
* Lomitusjärjestäminen
    * Lomitusjärjestämistä käytetään keskikokoisten ja suurten taulukoiden järjestämiseen
* Lisäysjärjestäminen
    * Lisäysjärjestämistä käytetään pienten taulukoiden järjestämiseen

## Ratkaistava ongelma
Gomokussa pelipuu kasvaa nopeasti erittäin suureksi, joten haasteena on karsia turhaa laskentaa ja löytää tehokas tapa arvioida siirtoja. Tekoälyn tavoite on pelata peliä mahdollisimman hyvin ja laskea peliä niin pitkälle, että se osaa tehdä pitkällä aikavälillä järkeviä siirtoja.

## Syötteet
Pelaaja painaa hiirellä graafisenkäyttöliittymän painiketta pelilaudalla ja kertoo näin, mihin haluaa sijoittaa nappulansa. Tekstikäyttöliittymässä pelaaja syöttää koordinaatit näppäimistöltä. Koordinaatit ilmoitetaan kirjaimen ja numeron yhdistelmänä samaan tapaan kuin esimerkiksi shakissa. Syöte määrittelee pelaajan siirron pelilaudalla.

## Tavoiteaika- ja tilavaativuudet
Tavoitteena on, että tekoäly pystyy laskemaan mahdollisimman syvälle pelipuuhun niin, että yksittäistä siirtoa ei joudu odottamaan 15 sekuntia pidempään. Loppupalautuksessa saavutettiin hakusyvyys pelipuun kuudennelle tasolle asti. Katso [Ohjelman puutteet ja jatkokehitysideat](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/toteutusdokumentti.md).

## Lähteet ja muuta tietoa
[Tietorakenteet ja algoritmit -kirja, Antti Laaksonen](https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/) Käytin tirakirjaa apuna toteuttaessani omat tietorakenteet.  
[Principal variation search](https://en.wikipedia.org/wiki/Principal_variation_search#Pseudocode) Vaikka tämä hakualgoritmin versio ei päätynyt loppupalautukseen, otin mallia tästä pseudokoodista tekoälyn toisen version toteutuksessa. Osa koodista jäi mukaan myös loppupalautukseen, vaikka varsinaista algoritmia ei loppupalautuksessa vielä ole.  
[JavaFX Game Tutorial: TicTacToe](https://www.youtube.com/watch?v=Uj8rPV6JbCE) Otin videolta mallia graafisenkäyttöliittymän toteuttamiseen.  
Opinto-ohjelma: Tietojenkäsittelytieteen kanditaatti  
Kieli: Suomi
