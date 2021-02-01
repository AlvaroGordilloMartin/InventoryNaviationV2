package com.example.inventorynavigation.data.repository;

import com.example.inventorynavigation.data.InventoryDatabase;
import com.example.inventorynavigation.data.dao.SectionDao;
import com.example.inventorynavigation.data.model.Seccion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SeccionesRepository {

    private List<Seccion> list;
    private static SeccionesRepository repository;
    private SectionDao sectionDao;

    static {
        repository = new SeccionesRepository();
    }

    public SeccionesRepository() {
        this.list = new ArrayList<>();
        sectionDao = InventoryDatabase.getDatabase().sectionDao();
    }


    public static SeccionesRepository getInstance() {
        return repository;
    }

    public static boolean Exists(Seccion seccion){
        return seccion.toString().isEmpty();
    }

    public List<Seccion> get(){
        try {
            list = InventoryDatabase.databaseWriteExecutor.submit(()->sectionDao.get()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            return list;
        }
    }

    public void add(Seccion seccion) {
        list.add(seccion);
    }

    public void Edit(Seccion seccion) {
        list.get(list.lastIndexOf(seccion)).Edit(seccion);
    }

    public void delete(Seccion seccion) {
        list.remove(seccion);
    }
}
