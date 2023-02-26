package service;

import toys.Toys;

import java.util.List;

public class ToysService {
    List<Toys> toysList;
    long id = 0;

    public ToysService() {
    }

    public List<Toys> getToysList() {
        return toysList;
    }

    public Toys createToys(long id, String nameToys, int quantity, int dropFrequency){
        return new Toys(id, nameToys, quantity, dropFrequency);
    }

    public Toys changeToys(Toys toy, int dropFrequency){
        toy.setDropFrequency(dropFrequency);
        return toy;
    }



}

