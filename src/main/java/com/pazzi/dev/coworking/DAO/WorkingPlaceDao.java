package com.pazzi.dev.coworking.DAO;

import com.pazzi.dev.coworking.Model.WorkingPlace;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Repository
@Transactional
public class WorkingPlaceDao implements Dao<WorkingPlace> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public WorkingPlaceDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //@Override
    @Transactional
    public Optional<WorkingPlace> fetchBy(UUID id) {
        return Optional.ofNullable(this.entityManager.find(WorkingPlace.class, id));
    }

    @Transactional
    public Optional<WorkingPlace> findByNameAndAddress(String name, String address) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<WorkingPlace> query = cb.createQuery(WorkingPlace.class);
        Root<WorkingPlace> root = query.from(WorkingPlace.class);

        Predicate namePredicate = cb.equal(root.get("name"), name);
        Predicate addressPredicate = cb.equal(root.get("address"), address);
        query.where(cb.and(namePredicate, addressPredicate));

        return entityManager.createQuery(query).getResultList().stream().findFirst();
    }

    //@Override
    @Transactional
    public List<WorkingPlace> getAll() throws IllegalArgumentException,
            IllegalStateException, QueryTimeoutException,
            TransactionRequiredException, PessimisticLockException, LockTimeoutException {
        Query query = entityManager.createQuery("SELECT e FROM WorkingPlace e");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(WorkingPlace workingPlace)
            throws EntityExistsException,
            IllegalArgumentException,
            TransactionRequiredException
    {
        entityManager.persist(workingPlace);
    }

    //@Override
    @Transactional
    public void update(WorkingPlace workingPlace, String[] params) {

    }

    //@Override
    @Transactional
    public void delete(WorkingPlace workingPlace) {
        this.executeInsideTransaction(entityManager1 -> entityManager.remove(workingPlace));
    }

    @Transactional
    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(this.entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
