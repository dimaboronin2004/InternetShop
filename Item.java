public class Item {
    public final Category category;
    public final String name;
    public final int price;
    public int amount;

    public Item(Category category, String name, int price, int amount) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.category.poolOfItems.add(this);
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void plus(int deliveredItems){
        this.amount += deliveredItems;
    }

    public void buy (int itemsToBuy) {
        this.amount -= itemsToBuy;
    }

    @Override
    public String toString() {
        String template = "Товар %s \n Категория: %s \n Цена: %d \n Количество: %d ";
        return String.format(template, name, category, price, amount);
    }
}
