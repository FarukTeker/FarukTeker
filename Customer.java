import java.util.Random;

public class Customer {
    private String name;
    private Ticket[] bookedTickets;
    private int numberOfTickets;

    // Constructor
    public Customer(String name, int numberOfTickets) {
        this.name = name;
        this.numberOfTickets = numberOfTickets;
        this.bookedTickets = new Ticket[numberOfTickets];
    }

    // Müşteri adını döndüren getter
    public String getName() {
        return name;
    }

    // Müşterinin rezervasyon yaptığı biletlerin kopyasını döndüren metod
    public Ticket[] getBookedTickets() {
        Ticket[] ticketsCopy = new Ticket[bookedTickets.length];
        for (int i = 0; i < bookedTickets.length; i++) {
            if (bookedTickets[i] != null) {
                ticketsCopy[i] = bookedTickets[i]; // Biletlerin deep copy'sini yapıyoruz
            }
        }
        return ticketsCopy;
    }

    // Bilet rezervasyonu yapan metod
    public void bookTicket(Ticket ticket, int index) {
        if(index>=0 && index < bookedTickets.length){
            bookedTickets[index] = ticket;
            ticket.setBooked(true);

        }
        else
            System.out.println("The index you entered is invalid.");
        
    }

    // Müşterinin tüm biletlerinin toplam fiyatını hesaplayan metod
    public double calculateTotalTicketPrice() {       
        double total = 0;
        for (Ticket ticket : bookedTickets) {
            if (ticket != null) {
                total += ticket.getPrice();
            }
        }
        return total;
    }
    
    public static void assignTicketsToCustomers(Customer[] customers, Venue venue) {
        Random random = new Random();
        for (Customer customer : customers) {
            for (int i = 0; i < customer.getBookedTickets().length; i++) {
                int randomSection = random.nextInt(venue.getSections().length);
                int randomRow = random.nextInt(venue.getSection(randomSection).getNumRows());
                int randomSeat = random.nextInt(venue.getSection(randomSection).getNumSeatsPerRow());
                customer.bookTicket(venue.getSection(randomSection).getTicket(randomRow, randomSeat), i);
            }
        }
    }
    
    // Müşterinin bilgilerini ve biletlerini gösteren toString metodu
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Customer: " + name + "\n");
        for (Ticket ticket : bookedTickets) {
            if (ticket != null) {
                result.append(ticket.toString()).append("\n");
            }
        }
        return result.toString();
    }
}