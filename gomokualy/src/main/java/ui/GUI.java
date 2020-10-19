
package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logiikka.Aly;
import logiikka.Lauta;

public class GUI extends Application {
    
    Stage nayttamo;
    
    Scene valikko;
    Scene peli;
    Scene pelisaannot;
    Scene asetukset;
    
    int leveys = 550;
    int korkeus = 550;
    
    GridPane pelilauta;
    VBox peliasetelma;
    Label vuorossa;
    
    Aly botti;
    boolean bottiPelaa;
    
    private Lauta lauta;

    @Override
    public void start(Stage nayttamo) {
        this.nayttamo = nayttamo;
        nayttamo.setTitle("Ristinolla");
        nayttamo.setResizable(false);
        
        valikko = valikko();
        peli = peli();
        pelisaannot = pelisaannot();
        asetukset = asetukset();
        
        nayttamo.setScene(valikko);
        nayttamo.show();
    }
    
    private Scene valikko() {
        VBox valikkoasetelma = new VBox(10);
        valikkoasetelma.setPadding(new Insets(10,10,10,10));
        valikkoasetelma.setAlignment(Pos.CENTER);
        
        Button saannot = new Button("Pelisäännöt");
        saannot.setOnAction(e -> nayttamo.setScene(pelisaannot));
        Button aloita = new Button("Aloita peli");
        aloita.setOnAction(e -> nayttamo.setScene(asetukset));
        Button lopeta = new Button("Lopeta");
        lopeta.setOnAction(e -> Platform.exit());
        
        valikkoasetelma.getChildren().addAll(aloita, saannot, lopeta);
        return new Scene(valikkoasetelma, leveys, korkeus);
    }
    
    private Scene peli() {
        peliasetelma = new VBox();
        peliasetelma.setAlignment(Pos.CENTER);
        
        HBox ylavalikko = new HBox(20);
        ylavalikko.setAlignment(Pos.CENTER);
        ylavalikko.setPadding(new Insets(10,0,0,10));
        
        Button palaaValikkoon = new Button("Takaisin valikkoon");
        palaaValikkoon.setOnAction(e -> nayttamo.setScene(valikko));
        vuorossa = new Label("");
        
        ylavalikko.getChildren().addAll(palaaValikkoon, vuorossa);
        
        peliasetelma.getChildren().addAll(ylavalikko);
        return new Scene(peliasetelma, leveys, korkeus);
    }
    
    private Scene asetukset() {
        VBox peliasetukset = new VBox(10);
        peliasetukset.setAlignment(Pos.CENTER);
        peliasetukset.setPadding(new Insets(10,10,10,10));
        
        HBox pelaajaasetukset = new HBox(10);
        pelaajaasetukset.setAlignment(Pos.CENTER);
        Label vastustaja = new Label("Kuka on vastustajasi? ");
        Label ihminen = new Label("Ihminen");
        Label bottiteksti = new Label("Botti");
        ToggleGroup pelaajavalinnat = new ToggleGroup();
        RadioButton ihmisnappula = new RadioButton();
        ihmisnappula.setToggleGroup(pelaajavalinnat);
        RadioButton bottinappula = new RadioButton();
        bottinappula.setSelected(true);
        bottinappula.setToggleGroup(pelaajavalinnat);
        pelaajaasetukset.getChildren().addAll(vastustaja, ihminen, ihmisnappula, bottiteksti, bottinappula);
        
        HBox aloittajaasetukset = new HBox(10);
        aloittajaasetukset.setAlignment(Pos.CENTER);
        Label aloittaja = new Label("Kumpi aloittaa? ");
        Label mina = new Label("Minä");
        Label toinen = new Label("Vastustaja");
        ToggleGroup aloittajavalinnat = new ToggleGroup();
        RadioButton minanappula = new RadioButton();
        minanappula.setSelected(true);
        minanappula.setToggleGroup(aloittajavalinnat);
        RadioButton toinennappula = new RadioButton();
        toinennappula.setToggleGroup(aloittajavalinnat);
        aloittajaasetukset.getChildren().addAll(aloittaja, mina, minanappula, toinen, toinennappula);
        
        
        Button valmis = new Button("Aloita peli");
        valmis.setOnAction(e -> {
            
            lauta = new Lauta();
            vuorossa.setText("Vuorossa: RASTI");
            
            int laudankoko = lauta.getPituus();
            if(peliasetelma.getChildren().contains(pelilauta)) peliasetelma.getChildren().remove(pelilauta);
            pelilauta = luoPelilauta(laudankoko);
            peliasetelma.getChildren().add(pelilauta);
            
            if(bottinappula.isSelected()){
                botti = new Aly();
                botti.setPituus(laudankoko);
                bottiPelaa = true;
            } else if(ihmisnappula.isSelected()){
                bottiPelaa = false;
            }
            
            if(bottiPelaa){
                if (minanappula.isSelected()) botti.setMerkki('O');
                else {
                    botti.setMerkki('X');
                    int[] koordinaatit = botti.teeSiirto(lauta.getLauta());
                    int column = koordinaatit[1] + 1;
                    int row = koordinaatit[0];
                    Ruutu sijoitus = (Ruutu) haeNodePelilaudalta(row, column);
                    sijoitus.setMerkki(lauta.getVuoro());
                    lauta.sijoita(koordinaatit[0], koordinaatit[1]);
                }
            }
            
            nayttamo.setScene(peli);
        });
        
        peliasetukset.getChildren().addAll(pelaajaasetukset, aloittajaasetukset, valmis);
        return new Scene(peliasetukset, leveys, korkeus);
    }
    
    private GridPane luoPelilauta(int koko) {
        GridPane ristikko = new GridPane();
        ristikko.setPadding(new Insets(10,10,10,10));
        ristikko.setAlignment(Pos.CENTER);
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j <= koko; j++) {
                if (j == 0){
                    Label numero = new Label("" + (koko - i));
                    numero.setPadding(new Insets(2,2,2,2));
                    ristikko.add(numero, j, i);
                } else {
                    ristikko.add(new Ruutu(i, j - 1), j, i);
                }
            }
        }
        char a = 'A';
        for(int i = 1; i <= koko; i++){
            ristikko.add(new Label("   " + a++), i, koko);
        }
        return ristikko;
    }
    
    private Scene pelisaannot() {
        VBox saannotasetelma = new VBox();
        saannotasetelma.setAlignment(Pos.CENTER);
        
        HBox nappula = new HBox();
        nappula.setPadding(new Insets(10,10,10,10));
        nappula.setAlignment(Pos.CENTER);
        
        Button valikkoon = new Button("Takaisin valikkoon");
        valikkoon.setOnAction(e -> nayttamo.setScene(valikko));
        
        nappula.getChildren().addAll(valikkoon);
        
        Label selitys = new Label("Pelin tarkoituksena on saada viisi omaa nappulaa peräkkäin joko vaaka-, pystyriviin tai vinoittain."
                + "\nPeliä pelaa kaksi pelaajaa vuorotellen, toinen rastilla ja toinen nollalla."
                + "\nEnsimmäinen pelaaja, joka saa viisi omaa nappulaansa peräkkäin, voittaa pelin."
                + "\nRasti aloittaa.");
        selitys.setWrapText(true);
        selitys.setPadding(new Insets(10,10,10,10));
        selitys.setMaxWidth(200);
        saannotasetelma.getChildren().addAll(nappula, selitys);
        return new Scene(saannotasetelma, leveys, korkeus);
    }
    
    private void peliPaattyi() {
        if (lauta.getTasapeli()) vuorossa.setText("Tasapeli!");
        else if (lauta.getVuoro() == 'X') vuorossa.setText("Rasti voitti!");
        else vuorossa.setText("Nolla voitti!");
        for(Node node: pelilauta.getChildren()){
            node.setDisable(true);
        }
    }
    
    private class Ruutu extends StackPane {

        private Text merkki = new Text();
        private int x;
        private int y;

        public Ruutu(int i, int j) {
            x = i;
            y = j;
            setAlignment(Pos.CENTER);
            Rectangle rajat = new Rectangle(25,25);
            rajat.setFill(null);
            rajat.setStroke(Color.BLACK);
            getChildren().addAll(rajat, merkki);
            
            setOnMouseClicked(e -> {
                if(merkki.getText().isEmpty()){
                    merkki.setText("" + lauta.getVuoro());
                    if(lauta.sijoita(x, y)){
                        peliPaattyi();
                    } else {
                        if(bottiPelaa){
                            tulostaLauta();
                            int[] koordinaatit = botti.teeSiirto(lauta.getLauta());
                            int column = koordinaatit[1] + 1;
                            int row = koordinaatit[0];
                            Ruutu sijoitus = (Ruutu) haeNodePelilaudalta(row, column);
                            sijoitus.setMerkki(lauta.getVuoro());
                            if(lauta.sijoita(koordinaatit[0], koordinaatit[1])){
                                peliPaattyi();
                                return;
                            }
                        }
                        if(lauta.getVuoro() == 'X') vuorossa.setText("Vuorossa: RASTI");
                        else vuorossa.setText("Vuorossa: NOLLA");
                    }
                }
            });
        }
        
        public void setMerkki(char c) {
            merkki.setText(c + "");
        }

    }
    
    private Node haeNodePelilaudalta(int i, int j) {
        for(Node node : pelilauta.getChildren()){
            if(GridPane.getColumnIndex(node) == j && GridPane.getRowIndex(node) == i) return node;
        }
        return null;
    }
    
    private void tulostaLauta() {
        char[][] l = lauta.getLauta();
        for(int i = 0; i < lauta.getPituus(); i++){
            for(int j = 0; j < lauta.getPituus(); j++){
                System.out.print(l[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
}
