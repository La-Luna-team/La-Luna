package com.laluna.laluna.repository;

import com.laluna.laluna.domain.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
