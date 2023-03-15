package ToyStore.User;

import ToyStore.Toys.Toys;

import java.io.*;
import java.util.*;

public class User {

    Scanner userInput = new Scanner(System.in);

    ArrayList<Integer> listValues = new ArrayList<>();
    int n;

    public void makingChoice(List<Toys> toysListInStore, List<Toys> toysListWinners) {
        System.out.println("""
                Добро пожаловать в магазин игрушек!
                Посмотреть список всех игрушек введите 1.
                Участвовать в лотерее введите 2.
                Выйти в начальное меню введите 0.""");

        try {
            int entry = userInput.nextInt();
            if (entry == 1) {
                showingList(toysListInStore);
            } else if (entry == 2) {
                toysListWinners.clear();
                idList(toysListInStore);
                randomParticipants(toysListInStore, toysListWinners);
                showingWinnerApplicants(toysListWinners);
                System.out.println("Какую по счету игрушку вы желаете забрать?");
                catchingPresent(toysListWinners, toysListInStore);
            } else if (entry == 0) {
                return;
            } else {
                System.out.println("Проверьте вводимые значения. Допускаются лишь цифры 0, 1 или 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Проверьте вводимые значения. Допускаются лишь цифры 0, 1 или 2.");
        }
    }

    public void catchingPresent(List<Toys> toysListWinners, List<Toys> toysListInStore) {
        try {
            int presentNum = userInput.nextInt();
            if (presentNum < 1 || presentNum > 3) {
                System.out.println("К сожалению, таких игрушек нет, попытайте счастье в другой раз.");
                return;
            } else {
                for (int i = 0; i < toysListWinners.size(); i++) {
                    if ((i + 1) == presentNum) {
                        String present = toysListWinners.get(i).name;
                        System.out.println("Поздравляем, вот ваш выйгрыш! " + present);
                    }
                }
                String toyName = toysListWinners.get(presentNum - 1).name;
                try (PrintWriter writer = new PrintWriter(new FileWriter("Presents.txt", true))) {
                    writer.print(toyName);
                    writer.append('\n');
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                for (Toys toys : toysListInStore) {
                    if (Objects.equals(toys.getName(), toyName)) {
                        toys.deleteToy();
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("""
                    Проверьте вводимые значения. Допускаются лишь цифры.
                    """);
        }
    }

    public void idList(List<Toys> toysListInStore) {
        for (Toys toys : toysListInStore) {
            int i = toys.getId();
            listValues.add(i);
        }
    }

    public void randomToysId() {
        int min = Collections.min(listValues);
        int max = Collections.max(listValues);
        n = (int) (Math.random() * (max - min) + min);
    }

    public void randomParticipants(List<Toys> toysListInStore, List<Toys> toysListWinners) {
        randomToysId();
        for (Toys toys : toysListInStore) {
            if (Objects.equals(toys.getId(), n)) {
                toysListWinners.add(toys);
            }
        }
        if (toysListWinners.size() < 3) {
            randomParticipants(toysListInStore, toysListWinners);
        }
    }

    public void showingList(List<Toys> toysListInStore) {
        System.out.println("Вот перечень всех игрушек представленных в нашем магазине: ");
        for (Toys toys : toysListInStore) {
            System.out.print(toys.name.replace("[", " ").replace(",", "").replace("]", " ") + "   ");
        }
        System.out.println();
    }

    public void showingWinnerApplicants(List<Toys> toysListWinners) {
        System.out.println("Вот перечень призовых игрушек в сегодняшнем розыгрыше: ");
        for (Toys toys : toysListWinners) {
            System.out.print(toys.name.replace("[", " ").replace(",", "").replace("]", " ") + "   ");
        }
        System.out.println();
    }

}
