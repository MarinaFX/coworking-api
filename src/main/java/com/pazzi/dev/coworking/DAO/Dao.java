package com.pazzi.dev.coworking.DAO;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface Dao<T>{
    @Transactional
    Optional<T> fetchBy(UUID id);

    @Transactional
    List<T> getAll();

    @Transactional
    void save(T t);

    @Transactional
    void update(T t);

    @Transactional
    void delete(T t);

    @Transactional
    Optional<T>findByNameAndAddress(String name, String address);
}
