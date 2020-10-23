# Testausdokumentti
## Mitä on testattu
Ohjelmaan on kirjoitettu JUnit testit luokille Aly, Hakemisto, Lauta ja Lista. Tämän lisäksi itset toteutetuille tietorakenteille eli yksinkertaiselle hajautustaululle Hakemisto ja järjestettävälle taulukkolistalle Lista on tehty omat tehokkuustestit, joilla voi tarkistaa, että tavoiteaikavaativuudet on saavutettu. Tehokkuustestit on yksi suoritettava tiedosto, joka kertoo käyttäjälle mitä tietorakennetta testataan ja miten ja kertoo testien tulokset.  
* **TestAly**. Tämä luokka testaa tekoälyn toimintaa. Luokassa testataan, että tekoäly osaa reagoida pakottaviin siirtoihin eli ei annan vastustajan voittaa liian helposti, ja että kovakoodatut ensimmäiset siirrot toimivat.  
* **TestHakemisto**. Luokka testaa hakemiston toimintaa. Testeissä tarkistetaan muun muassa, että hakemistoon lisääminen ja sieltä alkion hakeminen toimivat, eli että alkio löytyy hakemistosta, kun sen sinne lisää ja että sen saa haettua takaisin. Tässä luokassa testataan myös, että sama avain ei voi päätyä useaan kertaan hakemistoon ja että tyhjentäminen toimii.  
* **TestLauta**. Luokka testaa, että pelilautaa kuvaava luokka toimii oikein. Tässä luokassa on testit eri suuntaisille voittotarkistuksille molemmilla pelimerkeillä ja erilaisissa tilanteissa. Luokka testaa siis että pelilauta tunnistaa, kun peli päättyy eli laudalla on viidensuora tai on tullut tasapeli.  
* **TestLista**. Luokka testaa listan toimintaa. Testeissä katsotaan että listaan lisääminen toimii eli lisätyt alkiot löytyvät listalta oikeilta paikoilta. Näiden lisäksi tarksitetaan, että listan järjestäminen järjestää alkiot ja kääntäminen kääntää listan.  

Automaattisten yksikkötestien lisäksi ohjelmaa on kokonaisuudessaan testattu aktiivisesti projektin edetessä käsin pelaamalla bottia vastaan. Näin on tarkistettu yksittäisten metodien lisäksi, että botti tekee järkeviä siirtoja, ei tee yksinkertaisia virheitä ja että eri luokat toimivat halutulla tavalla yhteen. Botti tuntuu pelaavan ihan hyvin tavallista ihmistä vastaan ja olen siihen tyytyväinen tämän kurssin ajan ja tavoitteiden puitteissa. Taitavalle pelaajalle botti kyllä häviää, sillä se ei näe useita kaksoisuhkia tällä hetkellä ja että botista saisi oikean vastuksen, sitä pitäisi vielä parantaa.

## Tehokkuustestit
Miten listan ja hakemiston tehokkuutta on testattu? Entä botin tehokkuutta? Luokka Tehokkuustestit testaa tietorakenteiden ja botin oleellisten toimintojen tehokkuutta.  
* Lista  
    Testeissä Lista-luokan suoriutumista verrataan Javan ArrayListiin ja Javan valmiisiin metodeihin. Selkeyden vuoksi sanotaan vielä, että tekstissä lista viittaa Lista-luokkaan.
    * Ensimmäisessä testikokonaisuudessa katsotaan kuinka kauan kestää lisätä alkioita listan loppuun. Tätä testataan sen vuoksi, että voidaan olla vakuuttuneita siitä, että taulukkolistan loppuun lisääminen todellakin on nopeaa ja että satunnaiset enemmän aikaa vievät operaatiot (katso [Saavutetut aikavaativuudet](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/toteutusdokumentti.md#saavutetut-aikavaativuudet)) kun Lista-luokan sisäistä taulukkoa kasvatetaan, eivät vaikuta oleellisesti Listan tehokkuutteen. Yhden testikerran tulos on keskiarvo 100:sta testikerrasta, lukuunottamatta suurimman syötekoon testiä. Testin tulos kertoo myös Javan ArrayListin keskiarvon samasta testistä vertailun vuoksi. Listoja testataan eri kokoisilla syötteillä, 10 000, 100 000, 1 000 000 ja 10 000 000 alkiota. Tässä on esimerkki tyypillisen suorituskerran tuloksista:  
    ![Listaan lisääminen](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/Kuvat/tiralabralista1.jpg).  
    Tulokset ovat odotetun mukaiset ja lista tuntuu toimivan tältä osin hyvin. Alkion hakeminen listasta on Lista-luokan sisäisen toteutuksen vuoksi melko ilmiselvästi vakioaikainen operaatio, joten sitä ei ole erikseen testattu.
    * Toisessa testikokonaisuudessa katsotaan kuinka kauan listan järjestäminen kestää. Yhden testin tulos on keskiarvo 10:stä testikerrasta, lukuunottamatta testiä suurimmalla syötekoolla. Tässäkin listan järjestämistä verrataan saman kokoisen ArrayListin järjestämiseen Javan Collections-luokan sort()-metodilla. Listoja testataan eri kokoisilla syötteillä, 10 000, 100 000, 1 000 000 ja 10 000 000 alkiota. Tässä on esimerkki tyypillisen suorituskerran tuloksista:  
    ![Listan järjestäminen](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/Kuvat/tiralabralista2.jpg).  
    Lista tuntuu toimivan odotetusti ja pysyy ihan hyvin ArrayListin mukana.
    * Kolmannessa testikokonaisuudessa katsotaan, kuinka kauan listan kääntäminen ympäri kestää. Yhden testin tulos on keskiarvo 10:sta testikerrasta, lukuunottamatta testiä suurimmalla syötekoolla. Listan kaanna()-metodia verrataan Javan Collections-luokan reverse()-metodiin. Listoja testataan eri kokoisilla syötteillä, 10 000, 100 000, 1 000 000 ja 10 000 000. Testissä listaan lisätään ensin syötekoon verran alkioita ja tämän jälkeen lista käännetään ympäri. Alkioiden lisääminen ei selkeyden vuoksi ole mukana mitatussa ajassa. Tässä esimerkki tyypillisen suorituskerran tuloksista:  
    ![Listan kääntäminen](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/Kuvat/tiralabralista3.jpg)  
    Tässäkin tulokset ovat onneksi odotusten mukaiset ja kääntäminen tuntuu sujuvan nopeasti.
    
* Hakemisto  
    * Hakemiston operaatioiden aikavaativuuteen vaikuttaa oleellisesti se, kuinka tehokas hajautus on. Hakemiston tehokkuustesti testaa, mikä on pisin hajautustaulun sisäisen taulukon lista, kun Hakemistoon on lisätty syötekoon verran alkioita. Tämä kertoo kuinka kauan yksi operaatio pahimmillaan kestää, kun hajautustaulussa on syötekoon verran alkioita. Hakemistoa testataan lisäämällä sinne 1000, 10 000, 100 000, 300 000 ja 500 000 alkiota. Nämä arvot perustuvat siihen, että yleensä tavallisessa pelissä yhden siirron aikana hajautustauluun lisätään maksimissaankin noin 300 000 alkiota ja 500 000 alkiolla saadaan vielä varmempi yläraja arviolle. Tässä on esimerkki tyypillisen suorituskerran tuloksista:  
    ![Hakemisto](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/Kuvat/tiralabrahakemisto1.jpg)  
    Tuloksista nähdään, että listojen pituudet tuntuvat pysyvän melko lyhyinä. 500 000 alkiolla pisimmän listan pituus on yleensä hyvin lähellä kymmentä, joten olemme tyytyväisiä tuloksiin.
    
* Botti  
    * Botin tehokkuuden mittariksi valitsin keskimääräisen ja pisimmän siirtoajan. Automaattisessa testissä botti pelaa esimerkkipelin itseään vastaan ja tästä lasketaan, kuinka kauan botilla meni keskimäärin aikaa yhden siirron tekemiseen ja mikä oli pisin aika, minkä vastustaja joutui odottamaan botin siirtoa. Tässä on esimerkkinä erään suorituskerran tulokset:  
    ![Botin esimerkkipeli](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/Kuvat/tiralabrabotti1.jpg)  
    Näyttää siltä että otti tekee siirtonsa melko nopeasti, eikä tekoälyn siirtoa tarvitse odottaa liian pitkään. Botin pelaama peli itseään vastaan on kuitenkin melko lyhyt ja pelin pituus vaikuttaa botin siirtoon käyttämän ajan pituuteen. Hieman pidemmässä ihmistä vastaan pelatussa pelissä botilla kestää keskimäärin 4 sekuntia tehdä siirto ja pisin odotusaika on yleensä 15 sekunnin luokkaa.
    
## Testikattavuus
Testien rivikattavuutta on seurattu Jacocolla. Tässä on jacocon raportti lopullisesta rivi- ja haaraumakattavuudesta.  
![jacocokoko](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/Kuvat/jacocokoko.jpg)  
Testikattavuusraportista on jätetty pois käyttöliittymän koodin sisältävä paketti. Raportin perusteella kaikki oleellinen ainakin tuntuu tulleen testattua. Tässä on vielä tarkemmin logiikka- ja apupakettien kattavuudet.  
![jacocologiikka](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/Kuvat/jacocologiikka.jpg)  
![jacocoapu](https://github.com/pinjaw/gomokualy/blob/master/Dokumentaatio/Kuvat/jacocoapu.jpg)
