# Testausdokumentti
## Mitä on testattu
Ohjelmaan on kirjoitettu JUnit testit luokille Aly, Hakemisto, Lauta ja Lista. Tämän lisäksi itset toteutetuille tietorakenteille eli yksinkertaiselle hajautustaululle Hakemisto ja järjestettävälle taulukkolistalle Lista on tehty omat tehokkuustestit, joilla voi tarkistaa, että tavoiteaikavaativuudet on saavutettu. Tehokkuustestit on yksi suoritettava tiedosto, joka kertoo käyttäjälle mitä tietorakennetta testataan ja miten ja kertoo testien tulokset.  
* **TestAly**. Tämä luokka testaa tekoälyn toimintaa. Luokassa testataan, että tekoäly osaa reagoida pakottaviin siirtoihin eli ei annan vastustajan voittaa liian helposti, ja että kovakoodatut ensimmäiset siirrot toimivat.  
* **TestHakemisto**. Luokka testaa hakemiston toimintaa. Testeissä tarkistetaan muun muassa, että hakemistoon lisääminen ja sieltä alkion hakeminen toimivat, eli että alkio löytyy hakemistosta, kun sen sinne lisää ja että sen saa haettua takaisin. Tässä luokassa testataan myös, että sama avain ei voi päätyä useaan kertaan hakemistoon ja että tyhjentäminen toimii.  
* **TestLauta**. Luokka testaa, että pelilautaa kuvaava luokka toimii oikein. Tässä luokassa on testit eri suuntaisille voittotarkistuksille molemmilla pelimerkeillä ja erilaisissa tilanteissa. Luokka testaa siis että pelilauta tunnistaa, kun peli päättyy eli laudalla on viidensuora tai on tullut tasapeli.  
* **TestLista**. Luokka testaa listan toimintaa. Testeissä katsotaan että listaan lisääminen toimii eli lisätyt alkiot löytyvät listalta oikeilta paikoilta. Näiden lisäksi tarksitetaan, että listan järjestäminen järjestää alkiot ja kääntäminen kääntää listan.  

Automaattisten yksikkötestien lisäksi ohjelmaa on kokonaisuudessaan testattu aktiivisesti projektin edetessä käsin pelaamalla bottia vastaan. Näin on tarkistettu yksittäisten metodien lisäksi, että botti tekee järkeviä siirtoja, ei tee yksinkertaisia virheitä ja että eri luokat toimivat halutulla tavalla yhteen. Botti tuntuu pelaavan ihan hyvin tavallista ihmistä vastaan ja olen siihen tyytyväinen tämän kurssin ajan ja tavoitteiden puitteissa. Taitavalle pelaajalle botti kyllä häviää, sillä se ei näe useita kaksoisuhkia tällä hetkellä ja että botista saisi oikean vastuksen, sitä pitäisi vielä parantaa.

## Tehokkuustestit
Miten Listan ja Hakemiston tehokkuutta on testattu? Entä botin tehokkuutta? Luokka Tehokkuustestit testaa tietorakenteiden ja botin oleellisten toimintojen tehokkuutta.
* Lista  
    Testeissä Lista-luokkaa verrataan Javan ArrayListiin. 
    * Ensimmäisessä testikokonaisuudessa katsotaan kuinka kauan kestää lisätä alkioita listan loppuun. Tätä testataan sen vuoksi, että voidaan olla vakuuttuneita siitä, että taulukkolistan loppuun lisääminen todellakin on nopeaa ja että satunnaiset enemmän aikaa vievät operaatiot (katso [Saavutetut aikavaativuudet](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/toteutusdokumentti.md#saavutetut-aikavaativuudet)) kun Lista-luokan sisäistä taulukkoa kasvatetaan, eivät vaikuta oleellisesti Listan tehokkuutteen. Yhden testikerran tulos on keskiarvo 100:sta testikerrasta. Testin tulos kertoo myös Javan ArrayListin keskiarvon samasta testistä vertailun vuoksi. Listoja testataan eri kokoisilla syötteillä, 10 000, 100 000 ja 1 000 000 alkiota. Tässä on erään suorituskerran tulokset: (lisätään kuva tähän).  
    Alkion hakeminen listasta on Lista-luokan sisäisen toteutuksen vuoksi melko ilmiselvästi vakioaikainen operaatio, joten sitä ei ole erikseen testattu.
    * Toisessa testikokonaisuudessa katsotaan kuinka kauan listan järjestäminen kestää. Yhden testin tulos on keskiarvo 10:stä testikerrasta. Tässäkin Listan järjestämistä verrataan saman kokoisen ArrayListin järjestämiseen Javan Collections-luokan sort()-metodilla. Listoja testataan eri kokoisilla syötteillä, 10 000, 100 000 ja 1 000 000 alkiota. Tässä on erään suorituskerran tulokset: (lisätään kuva tähän).
    * Kolmannessa testikokonaisuudessa katsotaan, kuinka kauan listan kääntäminen ympäri kestää. Yhden testin tulos on keskiarvo 100:sta testikerrasta. Listan kaanna()-metodia verrataan Javan Collections-luokan reverse()-metodiin. Listoja testataan eri kokoisilla syötteillä, 10 000, 100 000 ja 1 000 000. Testissä listaan lisätään ensin syötekoon verran alkioita ja tämän jälkeen lista käännetään ympäri. Alkioiden lisääminen ei selkeyden vuoksi ole mukana mitatussa ajassa. Tässä erään suorituskerran tulokset: (lisätään kuva tähän).
* Hakemisto
    
* Botti
    
## Testikattavuus
