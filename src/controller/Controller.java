package controller;

import service.FileService;
import service.ToysService;
import toys.Toys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    ToysService toysService = new ToysService();
    FileService fileService = new FileService();
    List<Toys> toysList = new ArrayList<>();

    public void addToy(String nameToy, int quantity, int dropFrequency) throws IOException {
        int size = toysList.size();
        int id = 0;
        if (size != 0) {
            id = (int) ((toysList.get(size - 1)).getToysId() + 1);
        } else {
            id = 1;
        }
        probabilityCheck(id, dropFrequency);
        toysList.add(toysService.createToys(id, nameToy, quantity, dropFrequency));
    }

    public void viewAllToys() throws IOException {
        notNullList();
        for (Toys item : toysList) {
            System.out.println(item);
        }
    }

    public void change(int id, int dropFrequency) throws IOException {
        notNullList();
        if (id < 1 | id > toysList.size()) {
            throw new IOException("id: неверное значение");
        }
        probabilityCheck(id, dropFrequency);
        int index = -1;
        for (int i = 0; i < toysList.size(); i++) {
            if ((toysList.get(i)).getToysId() == id) {
                index = i;
            }
        }
        if (index != -1) {
            toysList.set(index, toysService.changeToys(toysList.get(index), dropFrequency));
        } else {
            throw new IOException("игрушки с таким id нет");
        }
    }

    public void probabilityCheck(int id, int dropFrequency) throws IOException {
        int probabilitySum = 0;
        for (Toys toys : toysList) {
            if (toys.getToysId() != id)
                probabilitySum = probabilitySum + toys.getDropFrequency();
        }
        if (probabilitySum + dropFrequency > 100) {
            throw new IOException("общая сумма вероятности выпадения всех игрушек не может быть больше 100%");
        }
    }

    public long draw() throws IOException {
        Random random = new Random();
        Toys prize = null;
        List<Long> idList = new ArrayList<>();
        List<Integer> chance = new ArrayList<>();
        List<Long> list = new ArrayList<>();
        for (Toys toys : toysList) {
            if (toys.getQuantity() == 0) {
                idList.add(0L);
            }
            idList.add(toys.getToysId());
        }
        for (Toys toys : toysList) {
            chance.add(toys.getDropFrequency());
        }
        for (int i = 0; i < idList.size(); i++) {
            for (int j = 0; j < chance.get(i); j++) {
                list.add(idList.get(i));
            }
        }
        if (list.size() < 100) {
            for (int j = list.size(); j < 100; j++) {
                list.add(0L);
            }
        }
        int index = random.nextInt(101);
        long idToy = list.get(index - 1);
        if (idToy == 0) {
            throw new IOException("к сожалению вы ничего не выиграли, попробуйте еще раз :(");
        } else {
            for (Toys toys : toysList) {
                if (toys.getToysId() == idToy) {
                    prize = toys;
                }
            }
            System.out.println("Вы выиграли: " + prize.getNameToys());
            return idToy;
        }
    }

    public void rafflePrizes() throws IOException {
        notNullList();
        long id = draw();
        int index = 0;
        for (int i = 0; i < toysList.size(); i++) {
            if ((toysList.get(i)).getToysId() == id) {
                index = i;
            }
        }
        fileService.writePrize(toysList.get(index));
        toysList.set(index, toysService.changeQuantity(toysList.get(index)));
        if ((toysList.get(index).getQuantity() == 0)) {
            toysList.remove(index);
        }
    }

    public void notNullList() throws IOException {
        if (toysList.size() == 0) {
            throw new IOException("добавьте игрушку: ADDTOY");
        }
    }
}
