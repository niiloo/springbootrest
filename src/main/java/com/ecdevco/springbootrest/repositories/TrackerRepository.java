package com.ecdevco.springbootrest.repositories;

import com.ecdevco.springbootrest.entities.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackerRepository extends JpaRepository<Tracker, Long> {

}
