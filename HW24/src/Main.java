import java.util.*;

public class Main {
    private static final String NAME_REGEX = "[A-zА-я]+";
    private static final String NUM_REGEX = "7[0-9]{10}";
    private static TreeMap<String, Set<String>> phonebook = new TreeMap<>();

    public static void main(String[] args) {
        System.out.println("\t\t телефонная книга \n");

        while(true){
            System.out.println("Введите имя, номер или команду list, exit");
            String input = new Scanner(System.in).nextLine();
            if (input.equals("list")){
                print();
            } else if (input.equals("exit")) {
                System.out.println("Чао!");
                return;
            } else if (input.matches(NAME_REGEX)) {
                addByName(input);
            } else if (input.replaceAll("\\D+", "").matches(NUM_REGEX)) {
                addByNum(input);
            } else {
                System.out.println("Неверная команда: " + input + " можете продолжить.");
            }
        }
    }

    private static void addByName(String name) {
        if (phonebook.containsKey(name)){
            System.out.println("такой абонент уже есть в базе " + phonebook.get(name));
        }
        System.out.println("введите номер телефона для абонента " + name);
        String num = new Scanner(System.in).nextLine();
        num = num.replaceAll("\\D+", "");
        if (num.matches(NUM_REGEX)){
            if (searchNum(num)){
                return;
            }
            addToBook(name, num);
            System.out.println("номер сохранен!");
        } else {
            System.out.println("это не номер!");
        }
    }

    private static void addByNum(String num){
        if (searchNum(num)){
            return;
        }
        System.out.println("введите имя для абонента " + num);
        String name = new Scanner(System.in).nextLine();
        if (name.matches(NAME_REGEX)){
            addToBook(name, num);
        } else {
            System.out.println("проверьте правильность ввода имени!");
        }


    }
    private static void addToBook(String name, String num){
        if(phonebook.containsKey(name)){
            phonebook.get(name).add(num);
            System.out.println("Абоненту " + name + " добавлен номер " + num);
        } else {
            Set<String> nums = new TreeSet<>();
            nums.add(num);
            phonebook.put(name, nums);
            System.out.println("Абонент " + name + " с номером " + num + " успешно добавлен");
        }
    }

    private static boolean searchNum(String num){
        for (Map.Entry<String, Set<String>> contact : phonebook.entrySet()) {
                if (contact.getValue().contains(num)) {
                    System.out.println("абонент с таким номером уже есть в базе " + contact.getKey());
                    return true;
                }
        }
        return false;
    }





    private static void print(){
        if (phonebook.isEmpty()){
            System.out.println("Нет записей");
            return;
        }
        for (Map.Entry<String, Set<String>> contact : phonebook.entrySet()){
            System.out.println("Абонент: " + contact.getKey());
            for (String num : contact.getValue()){
                System.out.println("\t" + num);
            }
        }
    }
}


