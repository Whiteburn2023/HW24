import java.util.*;

public class Main {
    private static final String NAME_REGEX = "[A-zА-я]+";
    private static final String NUM_REGEX = "7[0-9]{10}";
    private static TreeMap<String, String> phonebook = new TreeMap<>();

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
            } else if (input.matches(NUM_REGEX)) {
                addByNum(input);
            }
        }
    }

    private static void addByName(String name) {
        if (phonebook.containsKey(name)){
            System.out.println("такой абонент уже есть в базе " + phonebook.get(name));
        }
        System.out.println("введите номер телефона для абонента " + name);
        String num = new Scanner(System.in).nextLine();
        if (num.matches(NUM_REGEX)){
            if (searchNum(num)){
                return;
            }
            phonebook.put(name, num);
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
            phonebook.put(name, num);
        } else {
            System.out.println("проверьте правильность ввода имени!");
        }


    }
    private static boolean searchNum(String num){
        for (Map.Entry<String, String> entry : phonebook.entrySet()) {
                if (entry.getValue().equals(num)) {
                    System.out.println("абонент с таким номером уже есть в базе " + entry.getKey());
                    return true;
                }
        }
        return false;
    }





    private static void print(){
        for (Map.Entry<String, String> contact : phonebook.entrySet()){
            System.out.println("Абонент: " + contact.getKey() + "\t номер телефона: " + contact.getValue());
        }
    }
}


