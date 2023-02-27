package view;

import controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class View {
    Controller controller = new Controller();

    public void run() throws IOException {
        Commands com = Commands.NONE;

        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown command");
                continue;
            }
            if (com == Commands.EXIT) return;
            switch (com) {
                case ADDTOY:
                    String nameToy = prompt("Введите название игрушки: ");
                    try {
                        int quantity = Integer.parseInt(prompt("Введите количество: "));
                        if (quantity < 1) {
                            throw new IOException("количество:неверное значение");
                        }
                        int dropFrequency = Integer.parseInt(prompt("Введите частоту выпадения в розыгрыше: "));
                        if (dropFrequency < 1 | dropFrequency > 100) {
                            throw new IOException("частота:неверное значение(1-100)");
                        }
                        controller.addToy(nameToy, quantity, dropFrequency);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case VIEW:
                    try {
                        controller.viewAllToys();
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case CHANGE:
                    try {
                        int id = Integer.parseInt(prompt("Введите id игрушки, которую хотите изменить: "));
                        int dropFrequency = Integer.parseInt(prompt("Введите частоту выпадения в розыгрыше(1-100): "));
                        if (dropFrequency < 1 | dropFrequency > 100) {
                            throw new IOException("частота:неверное значение(1-100)");
                        }
                        controller.change(id, dropFrequency);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case DRAW:
                    try {
                        controller.rafflePrizes();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
