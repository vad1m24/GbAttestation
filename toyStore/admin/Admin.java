package toyStore.admin;

import toyStore.toys.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Admin {

    Scanner userInput = new Scanner(System.in);
    int password = 1234;

    public void startChanging(List<Toys> toysListInStore) {
        System.out.print("""
                Для получения доступа введите пароль.
                Для выхода в начальное меню введите 0.
                Password:\s""");
        try {
            int checkPasswd = userInput.nextInt();
            if (checkPasswd == 0) {
                return;
            }
            checkingPassword(password);
            if (checkingPassword(checkPasswd)) {
                System.out.println("""
                        Для добавления игрушек введите 1.
                        Для изменения % шанса выпадения игрушек введите 2.
                        Для выхода в начальное меню введите 0.""");
                inputNum(toysListInStore);
            } else {
                System.out.println("Пароль не верный. Введите ещё раз.");
                startChanging(toysListInStore);
            }
        } catch (InputMismatchException e) {
            System.out.println("Проверьте вводимые значения. Допускаются лишь цифры.");
        }
    }

    public void inputNum(List<Toys> toysListInStore) {
        try {
            int x = userInput.nextInt();
            if (x == 1) {
                addCountToys(toysListInStore);
            } else if (x == 2) {
                addWeightDropChance(toysListInStore);
            } else if (x == 0) {
                return;
            } else {
                System.out.println("Проверьте вводимые значения. Допускаются лишь цифры 0, 1 или 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Проверьте вводимые значения. Допускаются лишь цифры 0, 1 или 2.");
        }
    }

    public void addWeightDropChance(List<Toys> toysListInStore) {
        try {
            System.out.println("Введите название игрушки, вероятность выпада которой будем изменять.");
            String name = userInput.next();
            System.out.println("Введите вес вероятности от 0 до 1.");
            double dropChance = userInput.nextDouble();
            for (Toys toys : toysListInStore) {
                if (Objects.equals(toys.getName(), name)) {
                    toys.addDropChance(dropChance);
                }
            }
            showingList(toysListInStore);
            try {
                System.out.println("Хотите изменить иные игрушки? (y/n)");
                String answer = userInput.next();
                if (Objects.equals(answer, "y")) {
                    addWeightDropChance(toysListInStore);
                } else if (Objects.equals(answer, "n")) {
                    startChanging(toysListInStore);
                } else {
                    System.out.println("Проверьте вводимые значения. Допускаются лишь буквы y или n.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Проверьте вводимые значения. Допускаются лишь буквы.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Проверьте вводимые значения. Допускаются лишь числа от 0 до 1.");
        }
    }

    public void addCountToys(List<Toys> toysListInStore) {
        try {
            System.out.println("Введите название игрушки, которую будем добавлять.");
            String name = userInput.next();
            System.out.println("Введите количество добавляемых игрушек.");
            int quantity = userInput.nextInt();
            for (Toys toys : toysListInStore) {
                if (Objects.equals(toys.getName(), name)) {
                    toys.addToy(quantity);
                }
            }
            showingList(toysListInStore);
            try {
                System.out.println("Хотите добавить иные игрушки? (y/n)");
                String answer = userInput.next();
                if (Objects.equals(answer, "y")) {
                    addCountToys(toysListInStore);
                } else if (Objects.equals(answer, "n")) {
                    startChanging(toysListInStore);
                } else {
                    System.out.println("Проверьте вводимые значения. Допускаются лишь буквы y или n.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Проверьте вводимые значения. Допускаются лишь буквы.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Проверьте вводимые значения. Допускаются лишь цифры 1 или 2.");
        }
    }

    public boolean checkingPassword(int passwd) {
        return passwd == password;
    }

    public void showingList(List<Toys> toysListInStore) {
        System.out.println(toysListInStore.toString().replace("[", " ").replace(",", "").replace("]", " "));
    }

}
