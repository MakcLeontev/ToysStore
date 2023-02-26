package controller;

import service.ToysService;
import toys.Toys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    ToysService toysService = new ToysService();
    List<Toys>toysList = new ArrayList<>();

    public void addToy(String nameToy, int quantity, int dropFrequency){
        int id = toysList.size()+1;
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
        toysList.set(id-1,toysService.changeToys(toysList.get(id - 1),dropFrequency));
    }

}
