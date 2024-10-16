import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {
    String fileName = "customers.csv";

    // Dosyadaki satır sayısını hesaplayan metod
    public int counterOfLine(String fileName) {
        int counter = 0;
        try (BufferedReader FileStream = new BufferedReader(new FileReader(fileName))) {  // try-with-resources
            String line;
            while ((line = FileStream.readLine()) != null) {
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı: " + fileName);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter - 1;
    }

    // Müşteri dizisi, satır sayısına göre dinamik olarak başlatılacak
    private Customer[] customers = new Customer[counterOfLine(fileName)];

    // Dosyadan müşteri verilerini okuyan metod
    public void getData(String fileName) {
        int index = 0;

        try (BufferedReader FileStream = new BufferedReader(new FileReader(fileName))) {  // try-with-resources
            String line;
            line = FileStream.readLine();
            while ((line = FileStream.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                int ticketNumber = Integer.parseInt(values[1]);
                customers[index] = new Customer(name, ticketNumber);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Müşteri dizisini döndüren getter
    public Customer[] getCustomers() {
        return customers;
    }
}
