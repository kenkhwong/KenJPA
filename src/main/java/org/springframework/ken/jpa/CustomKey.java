package org.springframework.ken.jpa;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class CustomKey<T> {

    @Transient
    private T value;

    public T getValue() {
        return value;
    }

    abstract protected T generate();

    @PostPersist
    private void processKey() {
        this.value = generate();
    }
}