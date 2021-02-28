package com.example.bibliotheque.repository;

import com.example.bibliotheque.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BorrowRepository  extends JpaRepository<Borrow, Long> {
}
