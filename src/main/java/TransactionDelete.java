import java.util.Scanner;

public class TransactionDelete {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id książki którą chcesz usunąć");
        Long id = scanner.nextLong();

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.deleteById(id);
    }
}
