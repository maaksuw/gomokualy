# Toteutusdokumentti
## Ohjelman rakenne
Ohjelma koostuu kolmesta pakkauksesta ja yhdestä testipakkauksesta.
* Logiikka
    * Tässä pakkauksessa on kaikki pelilogiikan kannalta oleelliset luokat. Tekoäly on toteutettu luokkaan Aly ja pelilauta luokkaan Lauta. Pelin käynnistävä Main-luokka löytyy myös tästä pakkauksesta. Logiikka-pakkauksessa on myös siirtoa kuvaava Siirto-luokka sekä pelitilannetta kuvaava Tilanne-luokka, joita käytetään kapseloimaan tietoa tekoälyn toteutuksessa.
* UI
    * Pakkaus sisältää pelin graafisen- ja tekstikäyttöliittymän rakentavan koodin. Graafisen käyttöliittymän koodi on luokassa GUI.java ja tekstikäyttöliittymän koodi on luokassa UI.java.
* Apu
     * Apu-pakkaus sisältää itse toteutetut tietorakenteet sekä muut yleishyödylliset luokat. Pakkaus sisältää taulukkolistaa kuvaavan Lista-luokan, hakemistoa kuvaavan Hakemisto-luokan sekä Matikka-luokan, jossa on omat metodit muutamille yleisille laskuoperaatioille.  
* Testipakkaus
    * Testipakkaus on jaettu vielä selkeyden vuoksi kahteen pakkaukseen. Pakkaus testit sisältää ohjelman JUnit-testit. Siellä on luokat TestAly, joka testaa tekoälyä, TestHakemisto, joka testaa hajautustaulun omaa toteutusta, TestLauta, joka testaa pelilaudan toimivuutta sekä TestLista, joka testaa järjestettävän listan omaa toteutusta.

## Saavutetut aikavaativuudet
* Listan operaatiot:
    * **hae(indeksi)**: O(1). Metodi palauttaa listan annetulla indeksillä olevan alkion.
    * **poista()**: O(1). Metodi poistaa ja palauttaa listan viimeisen alkion.
    * **lisaa()**: Keskimäärin O(1). Metodi lisää listan loppuun alkion. Operaatio saattaa laukaista listan kasvattamisen, jolloin opetaario vie aikaa O(n), kun n on listan alkoiden lukumäärä, mutta listan kasvattamista ei tapahtu liian usein.
    * **jarjesta()**: O(nlog(n)), missä n on listan alkioiden lukumäärä. Metodi järjestää listan alkiot kasvavaan järjestykseen. Metodi on toteutettu niin, että pienet listat järjestetään käyttämällä lisäysjärjestämistä ja keskikokoiset ja suuret listat järjestetään lomitusjärjestämisellä. Kahden algoritmin käyttämiselle tässä ei ole hirveästi muita perusteluja, kuin että halusin kokeilla toteuttaa järjestämisen näin huvin vuoksi. Lisäysjärjestämisen ja lomitusjärjestämisen aikavaativuudet on listattu vielä alempana.
    * **kaanna()**: O(n), missä n on listan alkioiden lukumäärä. Metodi kääntää listan toisinpäin. Kääntämiseen käytetään vakiomäärä muistia.
* Hakemiston operaatiot:
    * **lisaa()**: Keskimäärin O(1). Metodi lisää hajautustauluun arvon annetulla avaimella. Jos annetulla avaimella löytyy jo arvo, arvo päivitetään. Aikavaativuus riippuu hajautuksen onnistumisesta. Tästä enemmän alempana.
    * **hae()**: Keskimäärin O(1). Metodi palauttaa annetun avaimen arvon hajautustaulusta tai null, jos avaimelle ei ole tallennettu arvoa.
    * **onkoAvainta()**: Keskimäärin O(1). Metodi palautta true, jos annettu avain löytyy hajautustaulusta.
    * **tyhjenna()**: O(n), missä n on hajautustaulun koko eli paikkojen lukumäärä.  

Hakemiston operaatioiden aikavaativuudet riippuvat hajautuksen onnistumisesta. Ohjelman testeissä on mukana suoritettava tiedosto, joka arvioi listan ja hakemiston aikavaativuuksien toteutumista. Tehokkuustesteissä lisätään hakemistoon ohjelman kannalta tarpeeksi paljon alkioita ja katsotaan, mikä on näin syntynyt pisin lista hajautustaulun sisällä. Testeissä on mukana satunnaisuutta, joten saadut tulokset muuttuvat joka kerta, mutta suurimmillakin testattavilla arvoilla pisin listan pituus on lähes varmasti aina alle 10. Voidaan siis sanoa, että hakemiston operaatiot toimivat vakioajassa. Katso [Testausdokumentti](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/testausdokumentti.md).
* Lisäysjärjestäminen: O(n^2), kun n on listan alkioiden määrä.
* Lomitusjärjestäminen: O(n*log(n)), kun n on listan alkioiden määrä.

## Miten ratkaistavaa ongelmaa on lähestytty
Oleellinen ongelma tekoälyn tekemisessä on saada karsittua turhaa laskentaa niin, että tekoäly pystyy laskemaan mahdollisimman monta siirtoa eteenpäin ja näin tekemään parhaan mahdollisen valinnan ([Ratkaistava ongelma](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/maarittelydokumentti.md)). Miten pelipuuta karsitaan ohjelmassa?
* Ruutuja on 15*15 = 225. Solmun lasten määrää rajoitetaan sillä, että jokaista ruutua ei tarkastella mahdollisena siirtona, vaan ruutu otetaan huomioon vain, jos se on korkeintaan tietyn säteen päässä jostain toisesta pelimerkistä. Näin pelipuussa solmun lasten määrää saadaan karsittua niin, että turhia hyvin kauas keskeiseltä alueelta tehtäviä siirtoja ei tarkastella. Tarkasteltavien ruutujen määrä kasvaa pelin aikana ja on arviolta korkeimmillaan, kun noin puolet pelilaudasta on käytössä. 
* Alfa-beeta-karsinta. Antamalla minimax-algoritmille parametrinä ylä- ja alaraja saadaan nopeammin karsittua turhia haaroja pois.
* Alkioiden järjestäminen. Jokaisessa pelitilanteessa seuraavia siirtoja ei käydä läpi mekaanisessa järjestyksessä vasemmalta oikealle, vaan jokaiselle siirrolle annetaan alustava arvio siirron hyvyydestä ja siirrot käydään läpi niin, että parhaimmaksi arvioidut siirrot tutkitaan ensin. Tämä tehostaa alfa-beeta-karsinnan vaikutusta, kun ylä- ja alarajat saadaan mahdollisimman tarkoiksi aikaisessa vaiheessa, vaikka yksittäisen solmun kohdalla työtä tuleekin lisää.
* Pelitilanteiden taulukointi. Aina kun pelitilanteelle saadaan laskettua lopullinen arvio, se tallennetaan hajautustauluun. Ennen jokaisen pelitilanteen arvion laskemista, hajautustaulusta tarkistetaan ensin, onko tämän tilanteen arvo laskettu jo aikaisemmin. Tallennetut arvot nollataan jokaisen siirron välissä, jotta vanhat arvot eivät sekoita laskentaa.

## Ohjelman puutteet ja jatkokehitysideat
* **Tekoäly ei laske tarpeeksi pitkälle.** Tämä on isoin ohjelmaan jäänyt puute ja paras tapa korjata tämä on tehdä tekoälystä tehokkaampi. Tällä hetkellä tekoälyn voi voittaa tekemällä kaksoisuhkia, joihin se ei osaa reagoida oikein ja tarpeeksi ajoissa. Yhden tai kahden siirron syvemmälle laskeminen parantaisi tilannetta jo huomattavasti, mutta tällä hetkellä tekoälyllä menee liian kauan aikaa laskea pidemmälle kuin syvyyteen 6. Halusin tehdä tekoälystä paremman ja tehokkaamman, mutta loppujen lopuksi siihen ei jäänyt tarpeeksi aikaa ja tekoälyn parantaminen jäi jatkokehitysideaksi.

## Lähteet
Tässä on listattuna lähteet, joita olen käyttänyt projektia tehdessä.  
[Tietorakenteet ja algoritmit -kirja, Antti Laaksonen](https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/) Käytin tirakirjaa apuna toteuttaessani omat tietorakenteet.  
[Principal variation search](https://en.wikipedia.org/wiki/Principal_variation_search#Pseudocode) Vaikka tämä hakualgoritmin versio ei päätynyt loppupalautukseen, otin mallia tästä pseudokoodista tekoälyn toisen version toteutuksessa. Osa koodista jäi mukaan myös loppupalautukseen, vaikka varsinaista algoritmia ei loppupalautuksessa vielä ole.  
[JavaFX Game Tutorial: TicTacToe](https://www.youtube.com/watch?v=Uj8rPV6JbCE) Otin videolta mallia graafisen käyttöliittymän toteuttamiseen.
