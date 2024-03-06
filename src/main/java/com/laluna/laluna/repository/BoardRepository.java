package com.laluna.laluna.repository;

import com.laluna.laluna.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>{
    List<Board> findByCategory(String category);
}
