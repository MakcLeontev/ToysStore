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
    List<Toys>toysList = new ArrayList<>();

    public void addToy(String nameToy, int quantity, int dropFrequency) throws IOException {
        int id = toysList.size()+1;
        probabilityCheck(id, dropFrequency);
        toysList.add(toysService.createToys(id, nameToy, quantity, dropFrequency));
    }
    public void viewAllToys(){
        for (Toys item:toysList){
            System.out.println(item);
        }
    }
    public void change(int id, int dropFrequency) throws IOException {
        if ( id<1 | id>toysList.size()){
            throw new IOException("id: неверное значение");
        }
        probabilityCheck(id, dropFrequency);
        toysList.set(id-1,toysService.changeToys(toysList.get(id - 1),dropFrequency));
    }

    public void probabilityCheck(int id,int dropFrequency) throws IOException {
        int probabilitySum = 0;
        for (Toys toys:toysList){
            if(toys.getToysId() != id)
                probabilitySum = probabilitySum + toys.getDropFrequency();
            }
        if (probabilitySum + dropFrequency > 100){
            throw new IOException("общая сумма вероятности выпадения всех игрушек не может быть больше 100%");
        }
    }

    public long draw() throws IOException {
        Random random = new Random();
        List<Long>idList = new ArrayList<>();
        List<Integer>chance = new ArrayList<>();
        List<Long>list = new ArrayList<>();
        for (Toys toys:toysList){
            if (toys.getQuantity() == 0){
                idList.add(0L);
            }
            idList.add(toys.getToysId());
        }
        for (Toys toys:toysList){
            chance.add(toys.getDropFrequency());
        }
        for (int i = 0; i < idList.size() ; i++) {
            for (int j = 0; j < chance.get(i) ; j++) {
                list.add(idList.get(i));
            }
        }
        if(list.size()<100){
            for (int j = list.size(); j < 100; j++) {
                list.add(0L);
            }
        }
        System.out.println(list);
        int index = random.nextInt(101);
        long idToy = list.get(index-1);
        if(idToy == 0){
            throw new IOException("к сожалению вы ничего не выиграли, попробуйте еще раз :(");
        }else {
            Toys prize = toysList.get(((int) idToy)-1);
            System.out.println("Вы выиграли: "+prize.getNameToys());
            return idToy;
        }
    }
    public void rafflePrizes() throws IOException {
        long id = draw();
        fileService.writePrize(toysList.get((int) (id-1)));
        toysList.set((int) (id-1),toysService.changeQuantity(toysList.get((int) (id-1))));
    }

}
