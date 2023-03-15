package ToyStore.Program;

import ToyStore.Admin.Admin;
import ToyStore.Toys.*;
import ToyStore.User.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Program {

    Scanner userInput = new Scanner(System.in);

    public List<Toys> toysListInStore = new ArrayList<>();
    public List<Toys> toysListWinners = new ArrayList<>();

    public void preparingProgram() {
        toysListInStore.add(new Robots(1, "Robot", 10, 0.2));
        toysListInStore.add(new Cars(2, "Car", 10, 0.2));
        toysListInStore.add(new BoardGames(3, "Board Game", 10, 0.2));
        toysListInStore.add(new Dolls(4, "Doll", 10, 0.2));
        toysListInStore.add(new Constructors(5, "Constructor", 10, 0.2));
    }

    public void startProgram() {
        System.out.println("Войти в приложение с правами владельца - 1\n" +
                "Войти в приложение в качестве покупателя - 2");
        try {
            int num = userInput.nextInt();
            if (num == 1) {
                Admin admin = new Admin();
                admin.startChanging(toysListInStore);
            } else if (num == 2) {
                User user = new User();
                user.makingChoice(toysListInStore, toysListWinners);
            } else {
                System.out.println("Проверьте вводимые значения. Допускаются лишь цифры 1 или 2.!");
            }
        } catch (InputMismatchException e) { // Помеять exception для нормального вывода!
            System.out.println("Проверьте вводимые значения. Допускаются лишь цифры 1 или 2...");
        }
        checkingAnswer();

    }

    private void checkingAnswer() {
        try {
            System.out.println("Желаете продолжить? (y/n)");
            String answer = userInput.next();
            if (Objects.equals(answer, "y")) {
                startProgram();
            } else if (Objects.equals(answer, "n")) {
                System.out.println("Ждём вас снова в нашем магазине!");
                try {
                    Files.delete(Paths.get("./Presents.txt"));
                } catch (IOException x) {
                    System.err.println(" ");
                };
                return;
            } else {
                System.out.println("Проверьте вводимые значения. Допускаются лишь буквы y или n.");
                checkingAnswer();
            }
        } catch (InputMismatchException e) {
            System.out.println("Проверьте вводимые значения. Допускаются лишь буквы y или n.");
        }
    }

}
