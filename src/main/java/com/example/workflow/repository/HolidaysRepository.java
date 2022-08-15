package com.example.workflow.repository;

import com.example.workflow.domain.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface HolidaysRepository extends JpaRepository<Holidays, Long> {
    List<Holidays> findByDateofholiday(Date date);

    @Modifying
    @Query(nativeQuery = true, value = "" +
            "INSERT INTO people (first_name, last_name) " +
            "VALUES('test', 'test') ")
    public void insertRow();
}
