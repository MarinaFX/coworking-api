package com.pazzi.dev.coworking.Service;

import java.util.List;

public interface Service<T> {



    public List<T> getAllPlaces();

    public T getPlaceBy(String name);

}
