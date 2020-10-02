# Toteutusdokumentti
## Ohjelman rakenne
Ohjelma koostuu kolmesta pakkauksesta:
    * Logiikka:
    Tässä pakkauksessa on kaikki pelilogiikan kannalta oleelliset luokat, kuten tekoälyn Aly-luokka, pelilautaa kuvaava Lauta-luokka sekä pelin käynnistävä Main-luokka. Logiikka-pakkauksesta löytyy myös siirtoa kuvaava Siirto-luokka sekä pelitilannetta kuvaava Tilanne-luokka.
    * UI:
    Pakkaus sisältää pelin tekstikäyttöliittymän rakentavan ja käynnistävän koodin luokassa UI.
    * Apu:
    Apu-pakkaus sisältää itse toteutetut tietorakenteet sekä muut yleishyödylliset luokat. Pakkaus sisältää taulukkolistaa kuvaavan Lista-luokan, hakemistoa kuvaavan Hakemisto-luokan sekä Matikka-luokan, jossa on omat metodit muutamille yleisille laskuoperaatioille.
Tämän lisäksi ohjelmaan kuuluu yksi testipakkaus, joka sisältää ohjelman JUnit-testit. Testipakkauksessa on luokat TestAly, joka testaa tekoälyä sekä TestLauta, joka testaa pelilaudan toimivuutta.

## Saavutetut aikavaativuudet
* Listan operaatiot:
    * hae(indeksi): O(1). Metodi palauttaa listan annetulla indeksillä olevan alkion.
    * poista(): O(1). Metodi poistaa ja palauttaa listan viimeisen alkion.
    * lisaa(): 
    Keskimäärin O(1). Metodi lisää listan loppuun alkion. Operaatio saattaa laukaista listan kasvattamisen, jolloin opetaario vie aikaa O(n), kun n on listan alkoiden lukumäärä, mutta listan kasvattamista ei tapahtu liian usein.
* Lisäysjärjestäminen: O(n^2), kun n on listan alkioiden määrä.
* Lomitusjärjestäminen: O(n*log(n)), kun n on listan alkioiden määrä.

## Suorituskykyanalyysi
Oleellinen ongelma tekoälyn tekemisessä on saada karsittua turhaa laskentaa niin, että tekoäly pystyy laskemaan mahdollisimman monta siirtoa eteenpäin ja näin tekemään parhaan mahdollisen valinnan. Miten pelipuuta karsitaan ohjelmassa?
* Isolla pelilaudalla pelatessa ruutuja on 19*19 = 361. Solmun lasten määrää rajoitetaan sillä, että jokaista ruutua ei tarkastella mahdollisena siirtona, vaan ruutu otetaan huomioon vain, jos se on korkeintaan tietyn säteen päässä jostain toisesta pelimerkistä. Näin pelipuussa solmun lasten määrää saadaan karsittua niin, että turhia hyvin kauas keskeiseltä alueelta tehtäviä siirtoja ei tarkastella.
* Alfa-beeta-karsinta. Antamalla minimax-algoritmille parametrinä ylä- ja alaraja saadaan nopeammin karsittua turhia haaroja pois.
* Alkioiden järjestäminen. Jokaisessa pelitilanteessa seuraavia siirtoja ei käydä läpi mekaanisessa järjestyksessä vasemmalta oikealle, vaan jokaiselle siirrolle annetaan alustava arvio siirron hyvyydestä ja siirrot käydään läpi niin, että parhaimmaksi arvioidut siirrot tutkitaan ensin. Tämä tehostaa alfa-beeta-karsinnan vaikutusta, kun ylä- ja alarajat saadaan mahdollisimman tarkoiksi aikaisessa vaiheessa.
* Pelitilanteiden taulukointi. Aina kun pelitilanteelle saadaan laskettua lopullinen arvio, se tallennetaan hajautustauluun. Ennen jokaisen pelitilanteen arvion laskemista, hajautustaulusta tarkistetaan ensin, onko tämän tilanteen arvo laskettu jo aikaisemmin.

## Ohjelman puutteet ja jatkokehitysideat
Kirjoitetaan ne tänne.

## Lähteet
[Go-Moku and Threat-Space Search, L.V.Allis, H.J. van den Herik, M.P.H. Huntjens](https://pdfs.semanticscholar.org/f476/00662cadb0975f9cfd7867389efedda6f873.pdf)  
[Tietorakenteet ja algoritmit -kirja, Antti Laaksonen](https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/)  
