package ToyStore.Toys;


import java.util.InputMismatchException;

public class Toys {

    public int id;
    public String name;
    public int count;
    public double dropChance;

    public Toys(int id, String name, int count, double dropChance) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.dropChance = dropChance;
    }

    public void addDropChance(double amount) {
        try {
            double num = dropChance + amount;
            if (num >= 0 && num <= 1) {
                dropChance += amount;
            } else {
                System.out.println("Проверьте вводимое значение вероятности выйгрыша!\nОно не может быть больше одного и меньше нуля.");
            }
        } catch(InputMismatchException e) {
            System.out.println("Проверьте вводимые значения. Допускаются лишь цифры.");
        }
    }

    public void addToy(int quantity) {
        try {
            if (quantity > 0) {
                count += quantity;
            } else {
                System.out.println("Проверьте вводимое значение количества игрушек!\nОно не может быть меньше одного.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Проверьте вводимые значения. Допускаются лишь цифры.");

        }
    }

    public void deleteToy() {
        count--;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getDropChance() {
        return dropChance;
    }

    public void setDropChance(double dropChance) {
        this.dropChance = dropChance;
    }

    @Override
    public String toString() {
        return "Toys{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count='" + count +
                ", dropChance=" + dropChance +
                "}\n";
    }

}
