public class Section {
    private int id;
    private int numRows = 10; // Her bölümde sabit 10 sıra
    private int numSeatsPerRow = 60; // Her sırada sabit 60 koltuk
    private double maxPrice;
    private double minPrice;
    private Ticket[][] tickets;

    // Constructor
    public Section(int id, double maxPrice, double minPrice) {
        this.id = id;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.tickets = new Ticket[numRows][numSeatsPerRow];
        initializeTickets();
    }

    // Biletleri başlatan ve fiyatları belirleyen metod
    private void initializeTickets() {
        for (int row = 0; row < numRows; row++) {
            for (int seat = 0; seat < numSeatsPerRow; seat++) {
                double price;
                if (row == 0) {
                    price = maxPrice; // 1. sıradaki biletler en pahalı
                } else if (row == 1) {
                    price = maxPrice * 0.8; // 2. sıradaki biletler %80 fiyat
                } else {
                    // Diğer sıralar rastgele bir fiyat aralığında
                    price = minPrice + (Math.random() * (maxPrice - minPrice));
                }
                tickets[row][seat] = new Ticket(id, row, seat, price, false);
            }
        }
    }

    // Belirli bir biletin bilgilerini döndüren metod
    public Ticket getTicket(int row, int seat) {
        if (row < 0 || row >= numRows || seat < 0 || seat >= numSeatsPerRow) {
            System.out.println("Invalid row or seat number.");
            return null;
        }
        return tickets[row][seat];
    }

    // Bölümdeki toplam geliri hesaplayan metod
    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (int row = 0; row < numRows; row++) {
            for (int seat = 0; seat < numSeatsPerRow; seat++) {
                if (tickets[row][seat].isBooked()) {
                    totalRevenue += tickets[row][seat].getPrice();
                }
            }
        }
        return totalRevenue;
    }

    // Bölümdeki doluluk oranını hesaplayan metod
    public double calculateOccupancyRate() {
        int bookedTickets = 0;
        int totalTickets = numRows * numSeatsPerRow;
        for (int row = 0; row < numRows; row++) {
            for (int seat = 0; seat < numSeatsPerRow; seat++) {
                if (tickets[row][seat].isBooked()) {
                    bookedTickets++;
                }
            }
        }
        return (double) bookedTickets / totalTickets;
    }

    // Tickets array'inin copy'sini döndüren getter
    public Ticket[][] getTickets() {
        Ticket[][] ticketsCopy = new Ticket[numRows][numSeatsPerRow];
        for (int row = 0; row < numRows; row++) {
            for (int seat = 0; seat < numSeatsPerRow; seat++) {
                if (tickets[row][seat] != null) {
                    ticketsCopy[row][seat] = tickets[row][seat];
                }
            }
        }
        return ticketsCopy;
    }

    // Getter ve Setter metodları
    public int getId() {
        return id;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumSeatsPerRow() {
        return numSeatsPerRow;
    }

    public void setTickets(Ticket[][] tickets) {
        this.tickets = tickets;
    }
}
