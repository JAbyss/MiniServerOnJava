package com.notmorron.MiniServerOnJava.dbs.postgres.repository;

import com.notmorron.MiniServerOnJava.dbs.postgres.models.GreenhouseTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface GreenhouseRepository extends JpaRepository<GreenhouseTable, Integer> {

    List<GreenhouseTable> findByIdGreenhouse(int idGreenhouse);
}