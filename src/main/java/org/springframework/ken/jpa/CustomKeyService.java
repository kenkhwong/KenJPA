package org.springframework.ken.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

public abstract class CustomKeyService<K extends CustomKey<T>, T> {

    abstract protected Supplier<K> getKeyFactory();

    abstract protected JpaRepository<K,?> getKeyRepository();

    @Transactional
    public T nextKey() {
        JpaRepository<K,?> repository = getKeyRepository();
        K key = repository.save(getKeyFactory().get());
        repository.delete(key);

        return key.getValue();
    }
}
