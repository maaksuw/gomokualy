# Määrittelydokumentti

## Toteutettavat algoritmit ja tietorakenteet
* Minimax-algoritmi
    * Tällä algoritmilla toteutetaan pelin tekoäly
* Alfa-beeta-karsinta
    * Alfa-beeta-karsinnalla yritetään karsia pelipuuta niin, että turhaa laskentaa tehtäisiin mahdollisimman vähän
* Threat-space search algoritmi
* Taulukkolista yksinkertaisilla operaatioilla
    * Taulukkolistaa käytetään ohjelmassa siirtojen listaamiseen ja hajautustaulun sisäisessä toteutuksessa
* Hajautustaulu yksinkertaisilla opetaatioilla
    * Hajautustaulua käytetään ohjelmassa pelipuun solmujen arvojen taulukoimiseen
* Lomitusjärjestäminen
    * Lomitusjärjestämistä käytetään keskikokoisten ja suurten taulukoiden järjestämiseen
* Lisäysjärjestäminen
    * Lisäysjärjestämistä käytetään pienten taulukoiden järjestämiseen

## Ratkaistava ongelma
Gomokussa pelipuu kasvaa nopeasti erittäin suureksi, joten haasteena on karsia turhaa laskentaa ja löytää tehokas tapa arvioida siirtoja. Tekoälyn tavoite on pelata peliä mahdollisimman hyvin ja laskea peliä niin pitkälle, että tekoäly osaa tehdä pitkällä aikavälillä järkeviä siirtoja ja tunnistaa monimutkaisenkin uhan. Jää nähtäväksi, kuinka hyväksi tekoälyn kurssin aikana ehdin ja osaan tehdä.

## Syötteet
Pelaaja syöttää näppäimistöllä kirjoittamalla haluamansa siirron koordinaatit ohjelmalle. Koordinaatit ilmoitetaan kirjaimen ja numeron yhdistelmänä samaan tapaan kuin esimerkiksi shakissa. Syöte määrittelee pelaajan siirron pelilaudalla.

## Tavoiteaika- ja tilavaativuudet
Tavoitteena on, että tekoäly pystyy laskemaan mahdollisimman syvälle pelipuuhun niin, että yksittäistä siirtoa ei joudu odottamaan 15 sekuntia pidempään.

## Lähteet ja muuta tietoa
[Go-Moku and Threat-Space Search, L.V.Allis, H.J. van den Herik, M.P.H. Huntjens](https://pdfs.semanticscholar.org/f476/00662cadb0975f9cfd7867389efedda6f873.pdf)  
[Tietorakenteet ja algoritmit -kirja, Antti Laaksonen](https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/)  
Opinto-ohjelma: Tietojenkäsittelytieteen kanditaatti  
Kieli: Suomi
