package org.springframework.ken.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

public abstract class EntityGeneratorService<E, V> {

    private final Supplier<E> entityFactory;
    private final JpaRepository<E, ?> repository;

    public EntityGeneratorService(Supplier<E> entityFactory, JpaRepository<E, ?> repository) {
        this.entityFactory = entityFactory;
        this.repository = repository;
    }

    abstract protected V mapValueOf(E entity);

    @Transactional
    public V nextValue() {
        E entity = repository.save(entityFactory.get());
        repository.delete(entity);

        return mapValueOf(entity);
    }
}

