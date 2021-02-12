package prakticne_vezbe.pv06_lavirint_komplikovano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Prikaz extends Application {

    public static final Color STVARNO_BELA = Color.hsb(0, 0, 1.0);
    public static final Color BELA         = Color.hsb(0, 0, 0.95);
    public static final Color SVETLO_SIVA  = Color.hsb(0, 0, 0.8);
    public static final Color SIVA         = Color.hsb(0, 0, 0.6);
    public static final Color TAMNO_SIVA   = Color.hsb(0, 0, 0.4);
    public static final Color CRNA         = Color.hsb(0, 0, 0.2);
    public static final Color STVARNO_CRNA = Color.hsb(0, 0, 0.0);

    public static final Color SVETLO_CRVENA      = Color.hsb(  0, 0.2, 1.0);
    public static final Color SVETLO_NARANDZASTA = Color.hsb( 30, 0.2, 1.0);
    public static final Color SVETLO_ZUTA        = Color.hsb( 60, 0.2, 1.0);
    public static final Color SVETLO_ZELENA      = Color.hsb(120, 0.2, 1.0);
    public static final Color SVETLO_TIRKIZNA    = Color.hsb(180, 0.2, 1.0);
    public static final Color SVETLO_AZURNA      = Color.hsb(210, 0.2, 1.0);
    public static final Color SVETLO_PLAVA       = Color.hsb(240, 0.2, 1.0);
    public static final Color SVETLO_PURPURNA    = Color.hsb(270, 0.2, 1.0);
    public static final Color SVETLO_LJUBICASTA  = Color.hsb(300, 0.2, 1.0);
    public static final Color SVETLO_RUZICASTA   = Color.hsb(330, 0.2, 1.0);

    public static final Color CRVENA      = Color.hsb(  0, 0.9, 0.8);
    public static final Color NARANDZASTA = Color.hsb( 30, 0.9, 0.8);
    public static final Color ZUTA        = Color.hsb( 60, 0.9, 0.8);
    public static final Color ZELENA      = Color.hsb(120, 0.9, 0.8);
    public static final Color TIRKIZNA    = Color.hsb(180, 0.9, 0.8);
    public static final Color AZURNA      = Color.hsb(210, 0.9, 0.8);
    public static final Color PLAVA       = Color.hsb(240, 0.9, 0.8);
    public static final Color PURPURNA    = Color.hsb(270, 0.9, 0.8);
    public static final Color LJUBICASTA  = Color.hsb(300, 0.9, 0.8);
    public static final Color RUZICASTA   = Color.hsb(330, 0.9, 0.8);

    public static final Color TAMNO_CRVENA      = Color.hsb(  0, 0.8, 0.4);
    public static final Color TAMNO_NARANDZASTA = Color.hsb( 30, 0.8, 0.4);
    public static final Color TAMNO_ZUTA        = Color.hsb( 60, 0.8, 0.4);
    public static final Color TAMNO_ZELENA      = Color.hsb(120, 0.8, 0.4);
    public static final Color TAMNO_TIRKIZNA    = Color.hsb(180, 0.8, 0.4);
    public static final Color TAMNO_AZURNA      = Color.hsb(210, 0.8, 0.4);
    public static final Color TAMNO_PLAVA       = Color.hsb(240, 0.8, 0.4);
    public static final Color TAMNO_PURPURNA    = Color.hsb(270, 0.8, 0.4);
    public static final Color TAMNO_LJUBICASTA  = Color.hsb(300, 0.8, 0.4);
    public static final Color TAMNO_RUZICASTA   = Color.hsb(330, 0.8, 0.4);

    ////////////////////
    // Boje i stilovi //
    ////////////////////

    protected static Paint bojaPozadine = BELA;
    protected static Paint bojaOkvira = CRNA;
    protected static Map<Integer, Paint> bojaPoljaPozadina = new HashMap<>();
    protected static Map<Integer, Paint> bojaPoljaTekst = new HashMap<>();
    protected static Font font = Font.font("Arial", FontWeight.BOLD, 12);

    public static void boja(Paint pozadina, Paint okvir) {
        bojaPozadine = pozadina;
        bojaOkvira = okvir;
    }

    public static void boja(int vrednost, Paint bojaPozadine, Paint bojaTeksta) {
        if (bojaPozadine == null) {
            bojaPoljaPozadina.remove(vrednost);
        }
        bojaPoljaPozadina.put(vrednost, bojaPozadine);
        if (bojaTeksta == null) {
            bojaPoljaTekst.remove(vrednost);
        }
        bojaPoljaTekst.put(vrednost, bojaTeksta);
    }

    public static void boja(int vrednostMin, int vrednostMax, Color bojaMin, Color bojaMax, Paint bojaTeksta) {
        if (bojaMin == null || bojaMax == null) {
            throw new IllegalArgumentException();
        }
        int n = vrednostMax - vrednostMin;
        for (int i = 0; i <= n; i++) {
            int vrednost = vrednostMin + i;
            bojaPoljaPozadina.put(vrednost, bojaMin.interpolate(bojaMax, (double) (i) / n));
            if (bojaTeksta == null) {
                bojaPoljaTekst.remove(vrednost);
            }
            bojaPoljaTekst.put(vrednost, bojaTeksta);
        }
    }

    ////////////////
    // Geometrija //
    ////////////////

    private static double velicinaPolja = 32;
    private static double velicinaRazmaka = 4;
    private static int debljinaOkvira = 0;
    private static double debljinaPuta = velicinaPolja / 2;

    public static void velicina(double polje, double razmak, int okvir) {
        velicinaPolja = polje;
        velicinaRazmaka = razmak;
        debljinaOkvira = okvir;
        debljinaPuta = velicinaPolja / 2;
    }

    protected static double ukupnaDuzina(int brojPolja) {
        return x(brojPolja + debljinaOkvira, 0.0);
    }

    protected static double x(int indeks, double delta) {
        indeks = indeks + debljinaOkvira;
        return indeks * (velicinaPolja + velicinaRazmaka) + velicinaRazmaka + velicinaPolja * delta;
    }

    protected static double x(int indeks) {
        return x(indeks, 0.0);
    }

    protected static double k(int indeks) {
        return x(indeks, 0.5);
    }

    protected static double w(int count, double delta) {
        return (velicinaPolja + velicinaRazmaka) * count + velicinaRazmaka * (delta - 1);
    }

    protected static double w(int count) {
        return w(count, 0);
    }

    protected static double r() {
        return 2 * velicinaRazmaka;
    }

    /////////////
    // Sadrzaj //
    /////////////

    protected static String naslovProzora;
    protected static int sirinaMape;
    protected static int visinaMape;
    protected static FunkcijaZaMapu funkcijaZaMapu;

    @FunctionalInterface
    public static interface FunkcijaZaMapu {
        public int vrednostNa(int x, int y);
    }

    public static void mapa(String naslov, int sirina, int visina, FunkcijaZaMapu funkcijaZaMapu) {
        naslovProzora = naslov;
        sirinaMape = sirina;
        visinaMape = visina;
        Prikaz.funkcijaZaMapu = funkcijaZaMapu;
        pokreni();
    }

    protected static Map<String, FunkcijaZaPut> funkcijeZaPuteve = new LinkedHashMap<>();
    protected static Map<String, FunkcijaZaVrednostPuta> funkcijeZaVrednostiPuteva = new LinkedHashMap<>();
    protected static Map<String, Paint> bojeZaPuteve = new HashMap<>();

    @FunctionalInterface
    public static interface FunkcijaZaPut {
        public KorakPuta korak(int indeks);
    }

    @FunctionalInterface
    public static interface FunkcijaZaVrednostPuta {
        public String vrednost();
    }

    public static class KorakPuta {
        public int x, y;
    }

    public static KorakPuta korak(int x, int y) {
        KorakPuta korak = new KorakPuta();
        korak.x = x; korak.y = y;
        return korak;
    }

    public static void put(String naziv, FunkcijaZaPut funkcijaZaPut, FunkcijaZaVrednostPuta funkcijaZaVrednostPuta, Paint boja) {
        odradiKasnije(() -> {
            funkcijeZaPuteve.remove(naziv);
            funkcijeZaVrednostiPuteva.remove(naziv);
            bojeZaPuteve.remove(naziv);
            listaPuteva.remove(naziv);
            Canvas platno = platnaZaPuteve.remove(naziv);
            centerPane.getChildren().remove(platno);
            if (funkcijaZaPut != null && funkcijaZaVrednostPuta != null && boja != null) {
                funkcijeZaPuteve.put(naziv, funkcijaZaPut);
                funkcijeZaVrednostiPuteva.put(naziv, funkcijaZaVrednostPuta);
                bojeZaPuteve.put(naziv, boja);
                platno = new Canvas(Prikaz.platno.getWidth(), Prikaz.platno.getHeight());
                platno.setOpacity(0.5);
                platno.getGraphicsContext2D().setLineCap(StrokeLineCap.ROUND);
                platno.getGraphicsContext2D().setLineJoin(StrokeLineJoin.ROUND);
                platnaZaPuteve.put(naziv, platno);
                centerPane.getChildren().add(1, platno);
                listaPuteva.add(naziv);
                crtajPut(naziv);
            }
        });
    }

    public static <T> void put(String naziv, List<T> lista, Function<T, Integer> x, Function<T, Integer> y,  FunkcijaZaVrednostPuta funkcijaZaVrednostPuta, Paint boja) {
        FunkcijaZaPut fja = (int indeks) -> {
            try {
                T element = lista.get(indeks);
                return korak(x.apply(element), y.apply(element));
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        };
        put(naziv, fja, funkcijaZaVrednostPuta, boja);
    }

    public static void osveziPut(String naziv) {
        odradiKasnije(() -> {
            crtajPut(naziv);
            listaPuteva.set(listaPuteva.indexOf(naziv), naziv);
        });
        cekajAkoTreba();
    }

    /////////
    // GUI //
    /////////

    protected static StackPane centerPane;
    protected static ListView<String> listView;
    protected static ObservableList<String> listaPuteva = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        Label labelPutevi = new Label("Putevi:");
        listView = new ListView<>(listaPuteva);
        listView.setCellFactory(l -> new ListCell<String>() {
            public void updateItem(String naziv, boolean empty) {
                super.updateItem(naziv, empty);
                if (empty || naziv == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Circle icon = new Circle(8);
                    icon.setFill(bojeZaPuteve.get(naziv));
                    setGraphic(icon);
                    FunkcijaZaVrednostPuta fja = funkcijeZaVrednostiPuteva.get(naziv);
                    try {
                        setText(naziv + ": " + fja.vrednost());
                    } catch (RuntimeException e) {
                    }
                }
            }
        });

        Label labelAnimacija = new Label();
        Slider slider = new Slider(0, 4, 2);
        Button button = new Button(">");
        osveziPanelZaAnimaciju(labelAnimacija, button, slider.getValue());
        slider.valueProperty().addListener((value, oldValue, newValue) -> {
            osveziPanelZaAnimaciju(labelAnimacija, button, newValue.doubleValue());
        });
        button.setOnAction(e -> {
            zavrsiCekanje();
        });
        button.setMaxHeight(Double.MAX_VALUE);

        GridPane animationPane = new GridPane();
        animationPane.setHgap(6);
        animationPane.setVgap(6);
        ColumnConstraints column = new ColumnConstraints();
        column.setHgrow(Priority.ALWAYS);
        animationPane.getColumnConstraints().add(column);
        animationPane.add(labelAnimacija, 0, 0);
        animationPane.add(slider, 0, 1);
        animationPane.add(button, 1, 0, 1, 2);

        VBox rightPane = new VBox();
        rightPane.setPadding(new Insets(12, 12, 12, 12));
        rightPane.setSpacing(6);
        rightPane.getChildren().add(labelPutevi);
        listView.setPrefHeight(0);
        VBox.setVgrow(listView, Priority.ALWAYS);
        rightPane.getChildren().add(listView);
        VBox.setMargin(animationPane, new Insets(6, 0, 0, 0));
        rightPane.getChildren().add(animationPane);

        platno = new Canvas(ukupnaDuzina(sirinaMape), ukupnaDuzina(visinaMape));
        osveziMapu();
        centerPane = new StackPane(platno);

        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(centerPane);
        rootPane.setRight(rightPane);
        Scene scene = new Scene(rootPane);

        primaryStage.setTitle(naslovProzora + " - " + sirinaMape + " x " + visinaMape + "");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> podesiVreme(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        pokrenut.countDown();

    }

    protected static void osveziPanelZaAnimaciju(Label labela, Button dugme, double vrednost) {
        long vreme = podesiVreme(vrednost);
        String tekst = "Brzina animacije: " + String.format("%.2f", vreme / 1000.0) + "s";
        dugme.setVisible(vreme >= 1000);
        labela.setText(tekst);
    }

    /////////////
    // Crtanje //
    /////////////

    protected static Canvas platno;
    protected static Map<String, Canvas> platnaZaPuteve = new LinkedHashMap<>();

    protected static void osveziMapu() {
        crtajMapu(platno.getGraphicsContext2D(), platno.getWidth(), platno.getHeight());
    }

    protected static void crtajMapu(GraphicsContext gc, double sirina, double visina) {
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(font);
        crtajOkvir(gc, sirina, visina);
        for (int j = 0; j < visinaMape; j++) {
            for (int i = 0; i < sirinaMape; i++) {
                int sadrzaj = funkcijaZaMapu.vrednostNa(i, j);
                Paint bojaPozadina = bojaPoljaPozadina.get(sadrzaj);
                Paint bojaTekst = bojaPoljaTekst.get(sadrzaj);
                crtajPolje(gc, i, j, bojaPozadina, bojaTekst, "" + sadrzaj);
            }
        }
    }

    protected static void crtajOkvir(GraphicsContext gc, double sirina, double visina) {
        gc.setFill(bojaPozadine);
        gc.fillRect(0, 0, sirina, visina);
        if (debljinaOkvira <= 0) {
            return;
        }
        gc.setFill(bojaOkvira);
        gc.fillRoundRect(x(-debljinaOkvira), x(-debljinaOkvira), w(sirinaMape + 2 * debljinaOkvira), w(visinaMape + 2 * debljinaOkvira), r(), r());
        gc.setFill(bojaPozadine);
        gc.fillRoundRect(x(-1, 1), x(-1, 1), w(sirinaMape, 2), w(visinaMape, 2), r(), r());
    }

    protected static void crtajPolje(GraphicsContext gc, int x, int y, Paint bojaPozadina, Paint bojaTekst, String tekst) {
        if (bojaPozadina != null) {
            gc.setFill(bojaPozadina);
            gc.fillRoundRect(x(x), x(y), w(1), w(1), r(), r());
        }
        if (tekst != null && bojaTekst != null) {
            gc.setFill(bojaTekst);
            gc.fillText(tekst, x(x, 0.5), x(y, 0.5));
        }
    }

    protected static void crtajPut(String naziv) {
        Canvas platno = platnaZaPuteve.get(naziv);
        GraphicsContext gc = platno.getGraphicsContext2D();
        gc.clearRect(0, 0, platno.getWidth(), platno.getHeight());
        FunkcijaZaPut fja = funkcijeZaPuteve.get(naziv);
        Paint boja = bojeZaPuteve.get(naziv);
        double debljina = debljinaPuta;
        List<KorakPuta> koraci = new ArrayList<>();
        int i = 0; KorakPuta korak;
        while ((korak = fja.korak(i++)) != null) {
            koraci.add(korak);
        }
        crtajPut(gc, koraci, boja, debljina);
    }

    protected static void crtajPut(GraphicsContext gc, List<KorakPuta> koraci, Paint boja, double debljina) {
        gc.setStroke(boja);
        gc.setLineWidth(debljina);
        if (koraci.size() == 1) {
            KorakPuta k = koraci.get(0);
            gc.setFill(boja);
            gc.fillOval(k(k.x) - debljina / 2, k(k.y) - debljina / 2, debljina, debljina);
        }
        for (int j = 1; j < koraci.size(); j++) {
            KorakPuta k1 = koraci.get(j - 1);
            KorakPuta k2 = koraci.get(j);
            gc.strokeLine(k(k1.x), k(k1.y), k(k2.x), k(k2.y));
        }
    }

    ///////////////
    // Animacija //
    ///////////////

    protected static boolean nekoCeka = false;
    protected static long vremeCekanja = 1000;

    protected static synchronized void cekajAkoTreba() {
        long pocetak = System.currentTimeMillis();
        if (vremeCekanja == 0) {
            return;
        }
        nekoCeka = true;
        long vreme;
        boolean bioPrekid = Thread.interrupted();
        while (nekoCeka && (vreme = pocetak + vremeCekanja - System.currentTimeMillis()) > 0) {
            try {
                Prikaz.class.wait(vreme);
            } catch (InterruptedException e) {
                bioPrekid = true;
            }
        }
        if (bioPrekid) {
            Thread.currentThread().interrupt();
        }
        nekoCeka = false;
    }

    protected static synchronized void podesiVreme(long novoVremeCekanja) {
        vremeCekanja = novoVremeCekanja;
        Prikaz.class.notify();
    }

    protected static synchronized void zavrsiCekanje() {
        nekoCeka = false;
        Prikaz.class.notify();
    }

    protected static long podesiVreme(double stepen) {
        stepen = 5 - stepen;
        if (stepen <= 1) {
            podesiVreme(0);
            return 0;
        }
        long vrednost = (long) Math.pow(10, stepen);
        podesiVreme(vrednost);
        return vrednost;
    }

    ////////////////
    // Pokretanje //
    ////////////////

    protected static CountDownLatch pokrenut = new CountDownLatch(1);

    protected static void pokreni() {
        Runnable launcher = () -> {
            launch(new String[] {});
        };
        new Thread(launcher).start();
        boolean bioPrekid = Thread.interrupted();
        boolean ok = false;
        do {
            try {
                pokrenut.await();
                ok = true;
            } catch (InterruptedException e) {
            	bioPrekid = true;
            }
        } while (!ok);
        if (bioPrekid) {
            Thread.currentThread().interrupt();
        }
    }

    protected static void odradiKasnije(Runnable zadatak) {
        Platform.runLater(zadatak);
    }
}
