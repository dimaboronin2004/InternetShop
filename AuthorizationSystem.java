import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AuthorizationSystem {

    private static final List<Customer> listOfCustomers = new ArrayList<>();
    public Customer currentCustomer;
    public static final AuthorizationSystem AUTH_SYSTEM = new AuthorizationSystem();

    private AuthorizationSystem(){

    }

    public void regAndAuth(){
        requestNameAndSurname();
        Scanner scnr = new Scanner(System.in);
        String name = scnr.next();
        String surname = scnr.next();

        while (!validateInputData(name, surname)) {
             name = scnr.next();
             surname = scnr.next();
        }

        if (checkIfCustomerPresent(name, surname)){
            requestId();
            String id = scnr.next();

            while (!checkId(name, surname, id)) {
                System.out.println("Введите корректный ID. Напоминаем, он состоит из пяти латинских букв и десяти цифр");
                id = scnr.next();
            }

            authorizeExistingCustomer(name, surname);
        }
        else{
            registerNewCustomer(name, surname);
        }

    }

    public void registerNewCustomer(String name, String surname){
        Customer newCustomer = new Customer(name, surname);
        System.out.println("Вы успешно зарегистрированы. Ваш ID: " + newCustomer.getId());
        listOfCustomers.add(newCustomer);
        currentCustomer = newCustomer;
    }

    public void authorizeExistingCustomer(String name, String surname){

        for (Customer customer: listOfCustomers) {
            if (customer.getName().equals(name) && customer.getSurname().equals(surname)){
                currentCustomer = customer;
                break;
            }
        }

        System.out.println("Вход успешно выполнен" );
    }

    public boolean validateInputData(String name, String surname) {
        String regex = "[A-ZА-Я][a-zа-я]+";
        return Pattern.matches(regex, name) && Pattern.matches(regex, surname);
    }

    public boolean checkId(String name, String surname, String id){
        boolean resultOfIdCheck = false;

        for (Customer customer: listOfCustomers) {
            if (customer.getName().equals(name) &&
            customer.getSurname().equals(surname) &&
            customer.getId().equals(id)) {
                return true;
            }
        }

        return resultOfIdCheck;
    }

    public void requestNameAndSurname(){
        System.out.println("Введите имя и фамилию в корректном формате, например: Иван Иванов");
    }

    public void requestId(){
        System.out.println("Вы уже есть в системе. Введите ID, выданный Вам при регистрации");
    }

    public boolean checkIfCustomerPresent(String name, String surname){

        for (Customer customer: listOfCustomers){
            if (customer.getName().equals(name) && customer.getSurname().equals(surname)) {
                return true;
            }
        }

        return false;
    }
}
