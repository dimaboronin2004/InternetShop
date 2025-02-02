import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InternetShop {

    private final List<Category> listOfCategories = new ArrayList<>();
    private int currentCategoryNum = 0;
    private Category currentCategory;
    private int currentItemNum = 0;
    private final Scanner scnr = new Scanner(System.in);
    private final AuthorizationSystem authSystem;
    private final Basket basket;
    private boolean isBasketSelected = false;

    public static final InternetShop INTERNET_SHOP = new InternetShop();

    private InternetShop(){
         authSystem = AuthorizationSystem.AUTH_SYSTEM;
         greeting();
         authSystem.regAndAuth();
         basket = new Basket(authSystem.currentCustomer);
    }

    public void greeting(){
        System.out.println("Уважаемый покупатель, приветствуем Вас в нашем магазине!\n" +
                "Выбирайте интересующие Вас категории и товары\n" +
                "Для перехода к корзине введите 0");
    }

    public void fillShop(){
        Category tech = new Category("Техника");
        listOfCategories.add(tech);
        Item huaweiPhone = new Item(tech, "Мобильный телефон Huawei", 11000, 200);
        Item xiaomiPhone = new Item(tech, "Мобильный телефон Xiaomi", 20000, 150);
        Item redmiPhone = new Item(tech, "Мобильный телефон Redmi", 17000, 170);
        Item huaweiLaptop = new Item(tech, "Ноутбук Huawei", 80000, 90);
        Item hpLaptop = new Item(tech, "Ноутбук HP", 65000, 120);
        Item lenovoLaptop = new Item(tech, "Ноутбук Lenovo", 55000, 140);
        Item brotherPrinter = new Item(tech, "Принтер Brother", 45000, 130);
        Item hpPrinter = new Item(tech, "Принтер HP", 60000, 100);
        Item airpodsHeadphones = new Item(tech, "Наушники Airpods", 25000, 170);
        Item svenHeadphones = new Item(tech, "Наушники Sven", 3000, 500);

        Category books = new Category("Книги");
        listOfCategories.add(books);
        Item evgenyOnegin = new Item(books, "Евгений Онегин", 900, 1000);
        Item pushkin = new Item(books, "Стихи Пушкина", 600, 1500);
        Item lermontov = new Item(books, "Стихи Лермонтова", 650, 1400);
        Item esenin = new Item(books, "Стихи Есенина", 500, 2000);
        Item chekhov = new Item(books, "Рассказы Чехова", 700, 1300);

        Category entertainment = new Category("Развлечения");
        listOfCategories.add(entertainment);
        Item aerohockey = new Item(entertainment, "Аэрохоккей", 10000, 180);
        Item synthezator = new Item(entertainment, "Синтезатор", 30000, 65);
        Item billiard = new Item(entertainment, "Бильярд", 25000, 80);
        Item musicCenter = new Item(entertainment, "Музыкальный центр", 50000, 75);
        Item speaker = new Item(entertainment, "Колонка", 10000, 110);
        Item vrGlasses = new Item(entertainment, "Очки VR", 25000, 80);
        Item xbox = new Item(entertainment, "Игровая приставка Xbox", 50000, 110);
        Item playStation = new Item(entertainment, "Игровая приставка PlayStation", 60000, 80);


        Category sport = new Category("Спорт");
        listOfCategories.add(sport);
        Item bike = new Item(sport, "Велосипед", 8000, 250);
        Item fitnessBracelette = new Item(sport, "Фитнес-браслет", 2500, 600);
        Item dumbbels5 = new Item(sport, "Гантели 5кг", 1500, 450);
        Item dumbbels10 = new Item(sport, "Гантели 10кг", 2000, 400);
        Item dumbbels15 = new Item(sport, "Гантели 15кг", 2500, 300);
        Item cocoBar = new Item(sport, "Спортивный батончик Кокос", 200, 5000);
        Item bananaBar = new Item(sport, "Спортивный батончик Банан", 200, 5000);

        Category leisure = new Category("Досуг");
        listOfCategories.add(leisure);
        Item picByNums = new Item(leisure, "Картина по номерам", 900, 1000);
        Item puzzle100 = new Item(leisure, "Пазл 100 элементов", 700, 1500);
        Item puzzle500 = new Item(leisure, "Пазл 500 элементов", 1300, 800);
        Item monopoly = new Item(leisure, "Игра Монополия", 100, 1200);
        Item chess = new Item(leisure, "Шахматы", 800, 1600);
    }


    public void showCategories(){
        System.out.println("Категории, доступные в нашем магазине: ");

        for (int i = 0; i < listOfCategories.size(); i++) {
            System.out.println(i + 1 + ". " + listOfCategories.get(i).getName());
        }

    }

    public void selectCategory(){
        int categoryNum = 0;
        System.out.println("Выберите категорию по номеру: ");
        int sel = scnr.nextInt(); //Выбранный номер категории

        if (sel == 0){ //При sel = 0 переход к корзине
            isBasketSelected = true;
            showBasket();
        }
        else{ //При sel != 0 продолжение покупок
            categoryNum = sel;

            while (!(categoryNum > 0 && categoryNum <= listOfCategories.size())) {
                System.out.println("Введите корректное значение номера категории");
                categoryNum = scnr.nextInt();
            }

            currentCategoryNum =  categoryNum - 1;
            currentCategory = listOfCategories.get(currentCategoryNum);
        }
    }


    public void showItemsInCategory(){

        for (int i = 0; i < currentCategory.getSize(); i++){
            System.out.println(i + 1 + ". " + currentCategory.poolOfItems.get(i).toString());
        }

    }

    public void selectItem(){
        int itemNum = 0;
        System.out.println("Выберите товар в категории по номеру. Он будет добавлен в корзину: ");
        int sel = scnr.nextInt(); //Выбранный номер товара

        if (sel == 0){ //При sel = 0 переход к корзине
            isBasketSelected = true;
            showBasket();
        }
        else{ //При sel != 0 продолжение покупок
            itemNum = sel;

            while (!(itemNum > 0 && itemNum <= currentCategory.poolOfItems.size())) {
                System.out.println("Введите корректное значение номера товара");
                itemNum = scnr.nextInt();
            }

            currentItemNum =  itemNum - 1;

            try {
                basket.addItem(listOfCategories.get(currentCategoryNum).poolOfItems.get(currentItemNum));
            } catch (ItemNotFoundException e) {
                System.err.println(e.getMessage());
            }

        }
    }



    public void showBasket(){
        int i = 0;
        for (Item item: basket.getBasketList()){
            i++;
            System.out.println(i + ". " + item.toString());
        }
        System.out.println("Общая стоимость корзины: " + basket.getGeneralCost());
    }

    public void chooseItemsToDelete(){
        System.out.println("Хотите ли Вы убрать какие-либо товары из корзины? Укажите их номера через запятую\n" +
                "Если таких товаров нет, ответьте - нет");

        String numsToDelete = scnr.next();
        String regex = "[0-9+, ]+";

        if (numsToDelete.equals("нет")) {
            System.out.println("Итоговая корзина выглядит так:");
            showBasket();
        }
        else{

            while (!(numsToDelete.matches(regex))){
                System.out.println("Введите номера корректным образом. Образец: 1, 2, 3, ...");
                numsToDelete = scnr.nextLine();
            }

            List<String> listOfNumsToDelete = List.of(numsToDelete.split(", "));

            for (String str: listOfNumsToDelete) {
                int numToDelete = Integer.parseInt(str) - 1;
                basket.deleteItem(basket.getBasketList().get(numToDelete));
            }

            System.out.println("Итоговая корзина выглядит так:");
            showBasket();
        }

    }

    public void payForBasket(){

        try {
            basket.pay();
            System.out.println("Покупка успешно оплачена. Ваш баланс: " + basket.getCustomer().getBalance());
        } catch (NotEnoughMoneyException e) {

            System.out.println("Средств недостаточно для оплаты корзины. Пожалуйста, пополните баланс. \n " +
                    "На данный момент баланс равен " + basket.getCustomer().getBalance() + "\n" +
                    "Введите сумму для пополнения (не более 500 000): ");

            int sumToPut = scnr.nextInt();

            while (!(sumToPut >= basket.getGeneralCost() && sumToPut <= 500000)){
                System.out.println("Пожалуйста, введите корректную сумму - не меньше стоимости корзины и не больше 500.000");
                sumToPut = scnr.nextInt();
            }

            basket.getCustomer().putMoney(sumToPut);

            try {
                basket.pay();
                System.out.println("Покупка успешно оплачена. Ваш баланс: " + basket.getCustomer().getBalance());
            } catch (NotEnoughMoneyException ex) {
                //Обработка исключения здесь формальная, т.к. выше проверяется пополнение баланса на достаточную сумму
                throw new RuntimeException(ex);
            }
        }
    }

    public void exitOrStay(){
        System.out.println("Вы хотите выйти или остаться в системе?");
        String exitOrStay = scnr.nextLine();

        while (!(exitOrStay.equals("выйти") || (exitOrStay.equals("остаться")))) {
            System.out.println("Выберите корректный вариант - выйти или остаться");
            exitOrStay = scnr.nextLine();
        }

        if (exitOrStay.equals("выйти")) {
            System.out.println("Вы успешно вышли из системы");
            System.out.println("Зарегистрируйтесь или войдите, чтобы продолжить");
            authSystem.regAndAuth();
            isBasketSelected = false;
            baseCycle();
        }
        else{
            System.out.println("Хотите начать новую покупку? Тогда напомним Вам наш ассортимент");
            isBasketSelected = false;
            baseCycle();
        }
    }

    public void baseCycle(){
        showCategories();
        selectCategory();

        while (!isBasketSelected) {
            showItemsInCategory();
            selectItem();
        }

        chooseItemsToDelete();
        payForBasket();
        exitOrStay();
    }

}
