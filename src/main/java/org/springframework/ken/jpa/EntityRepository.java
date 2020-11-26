package org.springframework.ken.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
