package com.laluna.laluna.repository;

import com.laluna.laluna.domain.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<Photo, Long> {
}
