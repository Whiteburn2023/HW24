import java.util.*;

public class Main {
    private static final String NAME_REGEX = "[A-zА-я]+";
    private static final String NUM_REGEX = "7[0-9]{10}";
    private static TreeMap<String, Set<String>> phonebook = new TreeMap<>();

    public static void main(String[] args) {
        System.out.println("\t\t телефонная книга \n");

        while (true){
            System.out.println("Введите команду, имя или номер: ");
            String scanner = new Scanner(System.in).nextLine();
            if (scanner.equalsIgnoreCase("print")){
                System.out.println("Содержание телефонной книги: ");
                printAll();
            } else if (scanner.equalsIgnoreCase("exit")) {
                System.out.println("Чао! ");
                return;
            } else if (scanner.matches(NAME_REGEX)) {
                addByName(scanner);
            } else if (scanner.replaceAll("\\D+", "").matches(NUM_REGEX)) {
                addByNum(scanner.replaceAll("\\D+", ""));
            } else {
                System.out.println("Неверный ввод!");
            }
        }
    }

    private static void addToBook(String name, String num) {
        if(phonebook.containsKey(name)){
            phonebook.get(name).add(num);
            System.out.println("Абоненту " + name + " добавлен номер " + num);
        } else {
            Set<String > nums = new TreeSet<>();
            nums.add(num);
            phonebook.put(name, nums);
            System.out.println("Абонент " + name + " с номером " + num + " успешно добавлен");
        }
    }

    public static void addByName(String name){
        if (phonebook.containsKey(name)) {
            System.out.println("Абонент " + name + " уже существует!");
            System.out.println("Номера: " + phonebook.get(name));
        }
        System.out.println("Введите номер для абонента " + name + ": ");
        String num = new Scanner(System.in).nextLine();
        num = num.replaceAll("\\D+", "");
        if (!num.matches(NUM_REGEX)) {
            System.out.println("Это не номер!");
            return;
        }
        for (Map.Entry<String, Set<String>> contact : phonebook.entrySet()) {
            if (contact.getValue().contains(num)) {
                System.out.println("Номер " + num + " уже есть у другого абонента " + searchByNum(num));
                return;
            }
        }

        addToBook(name, num);
    }

    public static void addByNum(String num){
        for (Map.Entry<String, Set<String>> contact : phonebook.entrySet()) {
            if (contact.getValue().contains(num)) {
                System.out.println("Номер " + num + " уже есть у другого абонента " + searchByNum(num));
                return;
            }
        }

        System.out.println("Введите имя абонента для номера: " + num);
        String name = new Scanner(System.in).nextLine();
        if(!name.matches(NAME_REGEX)){
            System.out.println("Это не имя!");
            return;
        }
        addToBook(name, num);
    }

    public static void printAll(){
        if (phonebook.isEmpty()){
            System.out.println("Записи отсутствуют");
            return;
        }
        for (Map.Entry<String, Set<String>> contact : phonebook.entrySet()) {
            System.out.println("Абонент: " + contact.getKey());
            for (String num : contact.getValue()) {
                System.out.println("\t" + num);
            }
        }
    }
    public static String searchByNum(String num){
        String name = null;
    for (Map.Entry<String, Set<String>> contact : phonebook.entrySet()) {
            if (contact.getValue().contains(num)) {
                name = contact.getKey();
            }
        }
    return name;
    }
}