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
            }catch(IllegalArgumentException e){
                System.out.println("Unknown command");
                continue;
            }
            if (com == Commands.EXIT) return;
            switch (com) {
                case ADDTOY:
                    String nameToy = prompt("Введите название игрушки: ");
                    try {
                        int quantity = Integer.parseInt(prompt("Введите количество: "));
                        if (quantity < 1){
                            throw new IOException("количество:неверное значение");
                        }
                        int dropFrequency = Integer.parseInt(prompt("Введите частоту выпадения в розыгрыше: "));
                        if (dropFrequency<1 | dropFrequency>100){
                            throw new IOException("частота:неверное значение(1-100)");
                        }
                        controller.addToy(nameToy, quantity, dropFrequency);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        continue;

                    }
                    break;
                case CHANGE:
                    String id = prompt("Идентификатор пользователя: ");
                    try {

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case ADDPRIZE:

                    break;
                case GETTOY:

            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

}
