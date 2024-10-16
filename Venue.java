public class Venue {
    private Section[] sections;
    private int numSections = 4;

    // Constructor
    public Venue() {
        this.sections = new Section[numSections];
        for (int i = 0; i < numSections; i++) {
            // Her bir bölümün maksimum ve minimum fiyatları rastgele belirleniyor
            double maxPrice = 4000 - (i * 500) + Math.random() * 1000;
            double minPrice = 3000 - (i * 500) + Math.random() * 1000;
            sections[i] = new Section(i, maxPrice, minPrice);
        }
    }

    // En fazla gelir elde eden bölümü bulan metod
    public Section getHighestRevenueSection() {
        Section highestRevenueSection = sections[0];
        for (Section section : sections) {
            if (section.calculateTotalRevenue() > highestRevenueSection.calculateTotalRevenue()) {
                highestRevenueSection = section;
            }
        }
        return highestRevenueSection;
    }

    // Mekanın toplam gelirini hesaplayan metod
    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Section section : sections) {
            totalRevenue += section.calculateTotalRevenue();
        }
        return totalRevenue;
    }

    // Mekanın doluluk oranını hesaplayan metod
    public double calculateOccupancyRate() {
        double totalOccupancy = 0;
        for (Section section : sections) {
            totalOccupancy += section.calculateOccupancyRate();
        }
        return totalOccupancy / numSections;
    }


    // Bölümleri döndüren getter
    public Section[] getSections() {
        Section[] sectionsCopy = new Section[numSections];
        for (int i = 0; i < sections.length; i++) {
            sectionsCopy[i] = new Section(
                sections[i].getId(),
                sections[i].getMaxPrice(),
                sections[i].getMinPrice()
            );
            // Tickets array'inin copy'sini eklemek gerekebilir
            sectionsCopy[i].setTickets(sections[i].getTickets());
        }
        return sectionsCopy;
    }

    // Bölümü ID'ye göre döndüren metod
    public Section getSection(int id) {
        if (id >= 0 && id < numSections) {
            return sections[id];
        }
        return null;
    }
}