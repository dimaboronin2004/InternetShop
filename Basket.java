import java.util.ArrayList;
import java.util.List;

public class Basket {
    public final List<Item> basketList = new ArrayList<>();
    public int generalCost = 0;
    public Customer customer;


    public Basket(Customer customer) {
        this.customer = customer;
    }

    public void addItem(Item item) throws ItemNotFoundException{
        validateItemPresence(item);
        basketList.add(item);
        generalCost += item.getPrice();
        item.buy(1);
    }

    public void deleteItem(Item item){
        basketList.remove(item);
        generalCost -= item.getPrice();
        item.plus(1);
    }


    public int getGeneralCost() {
        return generalCost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Item> getBasketList() {
        return basketList;
    }

    public void pay() throws NotEnoughMoneyException {
        validateCustomerBalance();
        customer.spendMoney(generalCost);
        basketList.clear();
        generalCost = 0;
    }

    public void validateCustomerBalance() throws NotEnoughMoneyException {
        if (customer.getBalance() < generalCost) {
            throw new NotEnoughMoneyException("Ошибка: недостаточно средств на балансе");
        }
    }

    public void validateItemPresence(Item item) throws ItemNotFoundException {
        if (item.amount == 0) {
            throw new ItemNotFoundException("Ошибка: запрашиваемый товар отсутствует");
        }
    }
}
