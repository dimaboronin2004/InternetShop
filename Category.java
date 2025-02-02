import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Category {
    public final String name;
    public final List<Item> poolOfItems = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addItem(Item item) {
        if (!poolOfItems.contains(item)) {
            poolOfItems.add(item);
        }
    }

    public void deleteItem(Item item) {
        poolOfItems.remove(item);
    }

    public int getSize(){
        return poolOfItems.size();
    }

    public Item findItemByName (String itemName) throws ItemNotFoundException {

        for (Item item: poolOfItems){
            if (item.name.equals(itemName)) {
                return item;
            }
            else{
                throw new ItemNotFoundException("Ошибка: запрашиваемый товар отсутствует");
            }
        }

        return new Item(new Category(""), "", 0, 0);
    }

    public List<Item> sortByPriceUp() {
        return poolOfItems.stream().sorted(Comparator.comparing(Item::getPrice)).collect(Collectors.toList());
    }

    public List<Item> sortByPriceDown() {
        return sortByPriceUp().reversed();
    }


    public List<Item> sortByAmountUp() {
        return poolOfItems.stream().sorted(Comparator.comparing(Item::getAmount)).collect(Collectors.toList());
    }

    public List<Item> sortByAmountDown() {
        return sortByAmountUp().reversed();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
