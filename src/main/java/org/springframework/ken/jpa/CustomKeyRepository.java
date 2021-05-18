package org.springframework.ken.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomKeyRepository<K extends CustomKey<T>, T> extends JpaRepository<K,T> {
}
