import java.util.*;

class User {
    public String username;
    private String password;
    private int userId;
    public ArrayList<Klant> klanten;
    public ArrayList<Berekenen> berekeningen;

    Scanner scanner = new Scanner(System.in);

    public User(String username, String password, int userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.klanten = new ArrayList<>();
        this.berekeningen = new ArrayList<>();
    }

    public void setUsername() {
        System.out.print("Enter new username: ");
        this.username = scanner.nextLine();
    }

    void setPassword() {
        System.out.print("Enter new password: ");
        this.password = scanner.nextLine();
    }

    public int getUserId() {
        return userId;
    }

    public void login() {
        System.out.print("Enter username to login: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Enter password to login: ");
        String inputPassword = scanner.nextLine();

        if (this.username.equals(inputUsername) && this.password.equals(inputPassword)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }
}

class Klant {
    public String klantNaam;
    public ArrayList<Klantb> klantBestelling;
    public ArrayList<eventH> evenementHuur;

    public Klant(String klantNaam){
        this.klantNaam = klantNaam;
        this.klantBestelling = new ArrayList<>();
        this.evenementHuur = new ArrayList<>();
    }

    private void createKlantData(){
        // Simulatie van klantbestelling (verkoop)
        klantBestelling.add(new Klantb("Lichtset Pro", 2, 149.99));
        klantBestelling.add(new Klantb("DJ Controller", 1, 299.50));

        // Simulatie van evenementhuur
        eventH evenement1 = new eventH("Festival Podiumverlichting", "2025-07-10");
        evenement1.addItem("LED Moving Head");
        evenement1.addItem("Stage Spotlights");

        eventH evenement2 = new eventH("Conferentie Geluidssysteem", "2025-08-03");
        evenement2.addItem("PA Speakers");
        evenement2.addItem("Draadloze microfoons");

        evenementHuur.add(evenement1);
        evenementHuur.add(evenement2);
    }
}

class Klantb {
    public String productNaam;
    public int aantal;
    public double prijsPerStuk;

    public Klantb(String productNaam, int aantal, double prijsPerStuk) {
        this.productNaam = productNaam;
        this.aantal = aantal;
        this.prijsPerStuk = prijsPerStuk;
    }

    public double getTotaalPrijs() {
        return aantal * prijsPerStuk;
    }
}

class eventH {
    public String eventNaam;
    public String datum;
    public ArrayList<String> gehuurdeItems;

    public eventH(String eventNaam, String datum) {
        this.eventNaam = eventNaam;
        this.datum = datum;
        this.gehuurdeItems = new ArrayList<>();
    }

    public void addItem(String item) {
        gehuurdeItems.add(item);
    }
}

class Berekenen {
    public int materiaalId;
    public List<Rekening> rekening;

    public Berekenen(int materiaalId) {
        this.materiaalId = materiaalId;
        this.rekening = new ArrayList<>();
    }

    public String addMateriaal(Rekening r) {
        rekening.add(r);
        return "Materiaal toegevoegd: " + r.toString();
    }

    public String removeMateriaal(Rekening r) {
        if (rekening.remove(r)) {
            return "Materiaal verwijderd: " + r.toString();
        } else {
            return "Materiaal niet gevonden.";
        }
    }

    public List<Integer> getMateriaalPrijs() {
        List<Integer> prijzen = new ArrayList<>();
        for (Rekening r : rekening) {
            prijzen.add(r.getPrijs());
        }
        return prijzen;
    }

    public void showRekening() {
        System.out.println("Overzicht van de rekening:");
        for (Rekening r : rekening) {
            System.out.println(r);
        }
    }
}

class Rekening {
    private String materiaalNaam;
    private int aantal;
    private double prijs;

    public Rekening(String materiaalNaam, int aantal, double prijs) {
        this.materiaalNaam = materiaalNaam;
        this.aantal = aantal;
        this.prijs = prijs;
    }

    public int getPrijs() {
        return (int) (prijs * aantal);
    }

    public String getBeschrijving() {
        return aantal + "x " + materiaalNaam + " (€" + prijs + " per stuk)";
    }
}

class Factuur {
    private int factuurId;
    public int hoeveelheid;
    public boolean isBetaald;

    public Factuur(int factuurId, int hoeveelheid, boolean isBetaald) {
        this.factuurId = factuurId;
        this.hoeveelheid = hoeveelheid;
        this.isBetaald = isBetaald;
    }

    public String addBeschrijving() {
        return "Factuur ID: " + factuurId + ", Hoeveelheid: " + hoeveelheid + ", Betaald: " + isBetaald;
    }
}

class Huurfactuur extends Factuur {
    private List<Rekening> materiaalLijst;
    private Date huurTijd;
    private Date tijdTerug;

    public Huurfactuur(int factuurId, int hoeveelheid, boolean isBetaald, List<Rekening> materiaalLijst, Date huurTijd, Date tijdTerug) {
        super(factuurId, hoeveelheid, isBetaald);
        this.materiaalLijst = materiaalLijst;
        this.huurTijd = huurTijd;
        this.tijdTerug = tijdTerug;
    }

    public List<Rekening> getMateriaal() {
        return materiaalLijst;
    }

    public double getPrijsMateriaal() {
        return materiaalLijst.stream().mapToDouble(Rekening::getPrijs).sum();
    }

    public Date huurTijd() {
        return huurTijd;
    }

    public Date tijdTerug() {
        return tijdTerug;
    }

    @Override
    public String addBeschrijving() {
        return super.addBeschrijving() + ", Type: Huur, Prijs: €" + getPrijsMateriaal();
    }
}

class Verkoopfactuur extends Factuur {
    private List<Rekening> materiaalLijst;

    public Verkoopfactuur(int factuurId, int hoeveelheid, boolean isBetaald, List<Rekening> materiaalLijst) {
        super(factuurId, hoeveelheid, isBetaald);
        this.materiaalLijst = materiaalLijst;
    }

    public List<Rekening> getMateriaal() {
        return materiaalLijst;
    }

    public double getPrijsMateriaal() {
        return materiaalLijst.stream().mapToDouble(Rekening::getPrijs).sum();
    }

    public void removeMatUitOpslag() {
        System.out.println("Materiaal verwijderd uit opslag.");
    }

    @Override
    public String addBeschrijving() {
        return super.addBeschrijving() + ", Type: Verkoop, Prijs: €" + getPrijsMateriaal();
    }
}

class Email {
    public String emailId;
    private Factuur factuur;

    public Email(String emailId, Factuur factuur) {
        this.emailId = emailId;
        this.factuur = factuur;
    }

    public String getEmail() {
        return createEmail();
    }

    public String createEmail() {
        String header = "Geachte klant,\n\nHierbij ontvangt u de factuurgegevens:\n";
        String body = factuur.addBeschrijving();
        String footer = "\n\nMet vriendelijke groet,\nStageLink\n";
        return header + body + footer;
    }
}

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Database {
//    private static final String URL = "jdbc:mysql://localhost:3306/stagefact";
//    private static final String USER = "root";
//    private static final String PASSWORD = "";
//
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//}

public class Main {
    public static void main(String[] args) {

        Rekening r1 = new Rekening("Speaker", 2, 150.0);
        Rekening r2 = new Rekening("Versterker", 1, 300.0);
        List<Rekening> materiaalLijst = Arrays.asList(r1, r2);

        Factuur factuur1 = new Huurfactuur(102, 5, false, materiaalLijst, new Date(), new Date());
        Factuur factuur2 = new Verkoopfactuur(101, 2, true, materiaalLijst);

        System.out.println(factuur1.addBeschrijving());
        System.out.println(factuur2.addBeschrijving());

        Email email = new Email("klant123@voorbeeld.nl", factuur2);

        System.out.println(email.getEmail());
    }
}