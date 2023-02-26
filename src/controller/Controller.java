package controller;

import service.ToysService;
import toys.Toys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    ToysService toysService = new ToysService();
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

    public void draw(){
        List<Integer>id = new ArrayList<>();
        List<Integer>chance = new ArrayList<>();

    }

}
