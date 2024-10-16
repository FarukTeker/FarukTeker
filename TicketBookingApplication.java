public class TicketBookingApplication {

    public static void main(String[] args) {
        // 1. Mekanı (venue) oluşturuyoruz
        Venue venue = new Venue();

        // 2. Müşteri verilerini dosyadan okuyoruz
        String filePath = "customers.csv";  // Dosya yolu (kendi dosya yolunuzu ayarlayın)
        FileIO fileIO = new FileIO();
        fileIO.getData(filePath);
        Customer[] customers = fileIO.getCustomers();

        // 3. Müşterilere biletleri rastgele atıyoruz
        Customer.assignTicketsToCustomers(customers, venue);

        // 4. Sorguları çalıştırıyoruz ve sonuçları yazdırıyoruz
        Query.runQueries(venue, customers);
    }
}
