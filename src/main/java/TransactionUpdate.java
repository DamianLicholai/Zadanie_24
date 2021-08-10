import java.util.Scanner;

public class TransactionUpdate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji którą chcesz zmienić");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Podaj typ transakcji");
        String type = scanner.nextLine();

        System.out.println("Podaj opis");
        String description = scanner.nextLine();

        System.out.println("Podaj cenę");
        int amount = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Podaj datę transakcji");
        String date = scanner.nextLine();

        Transaction transaction = new Transaction(id, type, description, amount, date);

        TransactionDao transactionDao = new TransactionDao();

        transactionDao.update(transaction);


    }
}
