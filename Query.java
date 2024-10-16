public class Query {
    

    // En fazla gelir getiren bölümü ekrana yazdırır
    public static void highestRevenueSection(Venue venue) {
        Section highestRevenueSection = venue.getHighestRevenueSection();
        System.out.println("Highest Revenue Section: Section " + highestRevenueSection.getId());
    }

    // Mekanın toplam gelirini hesaplayıp ekrana yazdırır
    public static void totalVenueRevenue(Venue venue) {
        double totalRevenue = venue.calculateTotalRevenue();
        System.out.println("Total Venue Revenue: " + totalRevenue + " TL");
    }

    // Mekanın doluluk oranını hesaplayıp ekrana yazdırır
    public static void occupancyRate(Venue venue) {
        double occupancyRate = venue.calculateOccupancyRate() * 100; // Yüzdelik formatta
        System.out.println("Venue Occupancy Rate: " + occupancyRate + "%");
    }

    // En pahalı bileti bulup ekrana yazdırır
    public static void mostExpensiveTicket(Venue venue) {
        Ticket mostExpensiveTicket = null;
        for (Section section : venue.getSections()) {
            for (int row = 0; row < section.getNumRows(); row++) {
                for (int seat = 0; seat < section.getNumSeatsPerRow(); seat++) {
                    Ticket ticket = section.getTicket(row, seat);
                    if (mostExpensiveTicket == null || ticket.getPrice() > mostExpensiveTicket.getPrice()) {
                        mostExpensiveTicket = ticket;
                    }
                }
            }
        }
        if (mostExpensiveTicket != null) {
            System.out.println("Most Expensive Ticket: " + mostExpensiveTicket);
        }
    }

    // En fazla bilet ücreti ödeyen müşterinin biletlerini bulur
    public static void customerWithHighestTotal(Customer[] customers) {
        Customer highestPayingCustomer = null;
        double highestTotal = 0;
        for (Customer customer : customers) {
            double total = customer.calculateTotalTicketPrice();
            if (total > highestTotal) {
                highestTotal = total;
                highestPayingCustomer = customer;
            }
        }
        if (highestPayingCustomer != null) {
            System.out.println("Customer with the highest total: " + highestPayingCustomer.getName());
            System.out.println("Total paid: " + highestTotal + " TL");
            System.out.println("Booked Tickets:");
            for (Ticket ticket : highestPayingCustomer.getBookedTickets()) {
                System.out.println(ticket);
            }
        }
    }


    // Her bölümün doluluk oranını yazdıran metod
    public static void displayOccupancy(Venue venue) {
    Section[] sections = venue.getSections();  // Venue'den sections dizisini alıyoruz
    if (sections == null) {
        System.out.println("No sections available.");
        return;
    }

    for (Section section : sections) {
        System.out.println("Seat occupancies in Section " + section.getId() + ":");
        Ticket[][] tickets = section.getTickets();
        for (int row = 0; row < section.getNumRows(); row++) {
            for (int seat = 0; seat < section.getNumSeatsPerRow(); seat++) {
                if (tickets[row][seat].isBooked()) {
                    System.out.print("X "); // Dolu koltuklar için
                } else {
                    System.out.print("O "); // Boş koltuklar için
                }
            }
            System.out.println(); // Satır sonu için yeni satıra geç
        }
        System.out.println(); // Bölüm sonuna boşluk ekle
    }
}


    // Her bölümün doluluk durumunu 'O' ve 'X' ile ekrana yazdırır
    public static void runQueries(Venue venue, Customer[] customers) {
        System.out.println("=== Highest Revenue Section ===");
        highestRevenueSection(venue);

        System.out.println("\n=== Total Venue Revenue ===");
        totalVenueRevenue(venue);

        System.out.println("\n=== Venue Occupancy Rate ===");
        occupancyRate(venue);

        System.out.println("\n=== Customer with Highest Total ===");
        customerWithHighestTotal(customers);

        System.out.println("\n=== Most Expensive Ticket ===");
        mostExpensiveTicket(venue);

        System.out.println("\n=== Seat Occupancies ===");
        displayOccupancy(venue);
    }
}
