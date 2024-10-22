import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nТелефонная книга\n");
        Scanner input = new Scanner(System.in);
        String name;
        String number;

        while (true) {
            System.out.println("Введите команду, имя или номер абонента : ");
            String scanner = input.nextLine().trim();

            if (scanner.equalsIgnoreCase("list")){
                PhoneBook.printAll();
            } else if (scanner.equalsIgnoreCase("exit")) {
                return;
            } else if (PhoneBook.checkName(scanner)) {
                if (PhoneBook.getNumberByName(scanner).isEmpty()){
                System.out.println("Такого имени в телефонной книге нет\n" + "Введите номер телефона для абонента " + "\"" + scanner + "\"" + " : ");
                number = input.nextLine().trim();
                PhoneBook.addContact(scanner, number);
                System.out.println("Контакт сохранен!");
                } else {
                    System.out.println(PhoneBook.getNumberByName(scanner));
                }
            } else if (PhoneBook.checkNumber(scanner)) {
                if (PhoneBook.getNameByNumber(scanner).isEmpty()) {
                    System.out.println("Такого номера в телефонной книге нет\n" + "Введите имя для абонента " + "\"" + scanner + "\"" + " : ");
                    name = input.nextLine().trim();
                    PhoneBook.addContact(name, scanner);
                    System.out.println("Контакт сохранен!");
                }else {
                    System.out.println(PhoneBook.getNameByNumber(scanner));
                }
            } else {
                System.out.println("Неверный формат ввода!");
            }
        }
    }
}