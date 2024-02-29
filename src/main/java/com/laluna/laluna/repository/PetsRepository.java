package com.laluna.laluna.repository;

import com.laluna.laluna.domain.entity.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetsRepository extends JpaRepository<Pets, Long> {
}
