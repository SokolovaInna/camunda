package com.example.democamunda.repository;

import com.example.democamunda.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    @Query(value = "SELECT * from card where id in(:ids) ", nativeQuery = true)
    List<Card> getAllCardByIds(List<Integer> ids);

}
