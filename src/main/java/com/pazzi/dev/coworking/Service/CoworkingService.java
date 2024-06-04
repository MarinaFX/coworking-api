package com.pazzi.dev.coworking.Service;

import com.pazzi.dev.coworking.DAO.Dao;
import com.pazzi.dev.coworking.DAO.WorkingPlaceDao;
import com.pazzi.dev.coworking.Exceptions.CoworkingNotFoundException;
import com.pazzi.dev.coworking.Model.WorkingPlace;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TransactionRequiredException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Service
public class CoworkingService implements Service {

    private final List<WorkingPlace> coworkings;
    private final BeanFactory factory;

    private final Dao<WorkingPlace> workingPlaceDao;

    @Autowired
    public CoworkingService(BeanFactory factory, EntityManager entityManager) {
        this.coworkings = new ArrayList<>();
        this.factory = factory;
        this.workingPlaceDao = new WorkingPlaceDao(entityManager);
    }

    public WorkingPlace fetchBy(UUID id) {
        if (this.workingPlaceDao.fetchBy(id).isPresent()) {
            return this.workingPlaceDao.fetchBy(id).get();
        }
        else{
            throw new CoworkingNotFoundException();
        }
    }

    @Transactional
    public void saveCoworking(WorkingPlace coworking)
            throws EntityExistsException,
            IllegalArgumentException,
            TransactionRequiredException   {
        Optional<WorkingPlace> result = this.workingPlaceDao
                .findByNameAndAddress(coworking.getName(), coworking.getAddress());

        if (result.isPresent()) {
            throw new EntityExistsException();
        }else {
            this.workingPlaceDao.save(coworking);
        }
    }

    @Override
    public List<WorkingPlace> getAllPlaces() {
        return this.workingPlaceDao.getAll();
    }

    @Override
    public WorkingPlace getPlaceBy(String name) {
        return null;
    }
}
