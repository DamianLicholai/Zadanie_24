import java.util.Scanner;

public class TransactionByType {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj jakiego typu transakcje chcesz wyświetlić");
        String type = scanner.nextLine();

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.findByType(type);
    }
}
