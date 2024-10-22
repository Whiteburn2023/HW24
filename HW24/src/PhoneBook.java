import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    public static TreeMap<String, String> phonebook = new TreeMap<>();


    public static boolean checkName(String name){
        String regex = "[А-яA-z]+";
        return name.matches(regex);

    }
    public static boolean checkNumber(String number){
        String regex = "7[0-9]{10}";
        return number.matches(regex);
    }
    public static void addContact(String name, String number){
        if (checkName(name) && checkNumber(number)){
            phonebook.put(name, number);
        } else {
            System.out.println("проверьте ввод!");
        }
    }

    public static String getNameByNumber(String number) {
        String foundKey = null;
        for (Map.Entry<String, String> entry : phonebook.entrySet()){
            if (entry.getValue().equals(number)) {
                foundKey = entry.getKey();
                break;
            }
        }
        if (foundKey != null) {
            return ("Абонент с таким номером \n" + "Имя: " + foundKey + "\t номер: "+ number);
        } else {
            return "";
        }
    }

    public static String getNumberByName(String name) {
        if (phonebook.get(name) != null) {
            return phonebook.get(name);
        } else {
            return "";
        }
    }

    public static void printAll(){
        for (Map.Entry<String, String > entry : phonebook.entrySet()) {
            System.out.println("Имя: " + entry.getKey() + "\t номер: "+ entry.getValue());
        }
    }
}
