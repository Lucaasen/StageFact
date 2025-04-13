import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//class User {
//    private String username;
//    private String password;
//    private int userId;
//    Scanner scanner = new Scanner(System.in);
//
//    public User(String username, String password, int userId) {
//        this.username = username;
//        this.password = password;
//        this.userId = userId;
//    }
//
//    public void setUsername(String username){
//        this.username = scanner.nextLine();
//    }
//
//    public int getUserId(){
//        return userId;
//    }
//
//    private void setPassword(String password){
//        this.password = scanner.nextLine();
//    }
//
//    public void login(){
//        if((username.contentEquals(SLQ.username)&&(password.contentEquals(SQL.password))));
//    }
//}
public class User {
    public String username; // Volgens UML: public
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

    void setPassword() { // geen access modifier opgegeven in UML, dus default
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

//class Klant {
//    public String klantNaam;
//    public ArrayList<Klantb> klantBestelling;
//    public ArrayList<eventH> evenementHuur;
//
//    public Klant(String klantNaam){
//        this.klantNaam = klantNaam;
//    }
//
//    private void createKlantData(){
//
//    }
//
//}
public class Klant {
    public String klantNaam;
    public ArrayList<Klantb> klantBestelling;
    public ArrayList<eventH> evenementHuur;

    public Klant(String klantNaam){
        this.klantNaam = klantNaam;
        this.klantBestelling = new ArrayList<>();
        this.evenementHuur = new ArrayList<>();
    }

//    private void createKlantData(){
//        // Voorbeelddata – zou normaal via invoer of database komen
//        klantBestelling.add(new Klantb("Pizza", 2));
//        klantBestelling.add(new Klantb("Pasta", 1));
//
//        evenementHuur.add(new eventH("Verjaardagsfeest", "2025-05-20"));
//        evenementHuur.add(new eventH("Bedrijfsevent", "2025-06-15"));
//    }
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

public class Klantb {
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

public class eventH {
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

//class Berekenen {
//    public int materiaalId;
//    public List<rekening>rekening;
//
//    public Berekenen(int materiaalId){
//        this.materiaalId = materiaalId;
//    }
//
//    public String removeMateriaal(){
//        return (rekening.removeMateriaal);
//    }
//
//    public int getMateriaalPrijs(){
//        return materiaalPrijs;
//    }
//    public void showRekening(){
//
//    }
//}
public class Berekenen {
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

public class Rekening {
    public String materiaalNaam;
    public int materiaalAantal;
    public int materiaalPrijs;

    public Rekening(String materiaalNaam, int materiaalAantal, int materiaalPrijs) {
        this.materiaalNaam = materiaalNaam;
        this.materiaalAantal = materiaalAantal;
        this.materiaalPrijs = materiaalPrijs;
    }

    public int getPrijs() {
        return materiaalAantal * materiaalPrijs;
    }

    @Override
    public String toString() {
        return materiaalAantal + "x " + materiaalNaam + " (€" + materiaalPrijs + " per stuk)";
    }
}

class Factuur {
    private int factuurId;
    public int hoeveelheid;
    public boolean isBetaald = false;

    public Factuur(int factuurId, int hoeveelheid, boolean isBetaald){
        this.factuurId = factuurId;
        this.hoeveelheid = hoeveelheid;
        this.isBetaald = isBetaald;
    }

    public void addBeschrijving(){

    }
}

class Huurfactuur extends Factuur{
    @Override

}

class Verkoopfactuur extends Factuur{
    @Override

}

class Email {
    public String emailId;

    public Email(String emailId){
        this.emailId = emailId;
    }

    public String getEmail(){

    }

    public createEmail(){

    }

}




public class Main {
    public static void main(String[] args) {


    }
}