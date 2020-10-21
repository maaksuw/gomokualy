# Testausdokumentti
## Mitä on testattu
Ohjelmaan on kirjoitettu JUnit testit luokille Aly, Hakemisto, Lauta ja Lista. Tämän lisäksi itset toteutetuille tietorakenteille eli yksinkertaiselle hajautustaululle Hakemisto ja järjestettävälle taulukkolistalle Lista on tehty omat tehokkuustestit, joilla voi tarkistaa, että tavoiteaikavaativuudet on saavutettu. Tehokkuustestit on yksi suoritettava tiedosto, joka kertoo käyttäjälle mitä tietorakennetta testataan ja miten ja kertoo testien tulokset.  
* **TestAly**. Tämä luokka testaa tekoälyn toimintaa. Luokassa testataan, että tekoäly osaa reagoida pakottaviin siirtoihin eli ei annan vastustajan voittaa liian helposti, ja että kovakoodatut ensimmäiset siirrot toimivat.  
* **TestHakemisto**. Luokka testaa hakemiston toimintaa. Testeissä tarkistetaan muun muassa, että hakemistoon lisääminen ja sieltä alkion hakeminen toimivat, eli että alkio löytyy hakemistosta, kun sen sinne lisää ja että sen saa haettua takaisin. Tässä luokassa testataan myös, että sama avain ei voi päätyä useaan kertaan hakemistoon ja että tyhjentäminen toimii.  
* **TestLauta**. Luokka testaa, että pelilautaa kuvaava luokka toimii oikein. Tässä luokassa on testit eri suuntaisille voittotarkistuksille molemmilla pelimerkeillä ja erilaisissa tilanteissa. Luokka testaa siis että pelilauta tunnistaa, kun peli päättyy eli laudalla on viidensuora tai on tullut tasapeli.  
* **TestLista**. Luokka testaa listan toimintaa. Testeissä katsotaan että listaan lisääminen toimii eli lisätyt alkiot löytyvät listalta oikeilta paikoilta. Näiden lisäksi tarksitetaan, että listan järjestäminen järjestää alkiot ja kääntäminen kääntää listan.  

Automaattisten yksikkötestien lisäksi ohjelmaa on kokonaisuudessaan testattu aktiivisesti projektin edetessä käsin pelaamalla bottia vastaan. Näin on tarkistettu yksittäisten metodien lisäksi, että botti tekee järkeviä siirtoja, ei tee yksinkertaisia virheitä ja että eri luokat toimivat halutulla tavalla yhteen. Botti tuntuu pelaavan ihan hyvin tavallista ihmistä vastaan ja olen siihen tyytyväinen tämän kurssin ajan ja tavoitteiden puitteissa. Taitavalle pelaajalle botti kyllä häviää, sillä se ei näe useita kaksoisuhkia tällä hetkellä ja että botista saisi oikean vastuksen, sitä pitäisi vielä parantaa.
## Tehokkuustestin tuloksia
Miten Listan ja Hakemiston tehokkuutta on testattu? Entä botin tehokkuutta?
* Lista  
* Hakemisto
* Botti
