package com.laluna.laluna.repository;

import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.domain.entity.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetsRepository extends JpaRepository<Pets, Long> {
    List<Pets> findByMember_Mnum(Long mnum);
}
