package org.springframework.ken.jpa;

import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

public class CustomKeySupplier<K extends CustomKey<T>, T> implements Supplier<T> {

    private final CustomKeyRepository<K,T> keyRepo;
    private final Supplier<K> keyFactory;

    public CustomKeySupplier(CustomKeyRepository<K,T> keyRepo,
                             Supplier<K> keyFactory) {
        this.keyRepo = keyRepo;
        this.keyFactory = keyFactory;
    }

    @Override
    @Transactional
    public T get() {
        K key = keyRepo.save(keyFactory.get());
        keyRepo.delete(key);

        return key.getValue();
    }
}
