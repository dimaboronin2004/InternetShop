import java.util.Random;

public class Customer {
    public  String  name;
    public  String surname;
    public  String id;
    public int balance = 0;
    public static int numOfCustomers = 0;
    public Basket basket;

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = generateId();
        numOfCustomers++;
    }


    private String generateId() {
        StringBuilder ident = new StringBuilder();

        int spreadSymbols = 26;
        int delta = 97;
        //Формирование буквенной части идентификатора
        for (int i = 0; i < 5; i++) {
            Random r = new Random();
            int charCode = r.nextInt(spreadSymbols) + delta;
            char randomChar = (char) charCode;
            ident.append(randomChar);
        }

        int spreadDigits = 10;
        //Формирование цифровой части идентификатора
        for (int i = 0; i < 10; i++){
            Random r = new Random();
            int randomDigit = r.nextInt(spreadDigits);
            ident.append(randomDigit);
        }

        return String.valueOf(ident);
    }

    public void putMoney(int sumToPut){
        balance += sumToPut;
    }

    public void spendMoney(int sumToSpend){
        balance -= sumToSpend;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public static int getNumOfCustomers() {
        return numOfCustomers;
    }

    @Override
    public String toString() {
        String template = "Customer №%d \n ID: %s \n Name: %s \n Surname: %s ";
        return String.format(template, numOfCustomers, id, name, surname);

    }

    public Basket getBasket() {
        return basket;
    }


}
