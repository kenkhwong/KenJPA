package org.springframework.ken.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

public abstract class CustomKeyService<K extends CustomKey<T>, T> {

    private final Supplier<K> keyFactory;
    private final JpaRepository<K,?> repository;

    public CustomKeyService(Supplier<K> keyFactory, JpaRepository<K,?> repository) {
        this.keyFactory = keyFactory;
        this.repository = repository;
    }

    @Transactional
    public T nextKey() {
        K key = repository.save(keyFactory.get());
        repository.delete(key);

        return key.getValue();
    }
}
