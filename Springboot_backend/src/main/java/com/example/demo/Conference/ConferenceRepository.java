package com.example.demo.Conference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference,Long> {

    @Override
    Optional<Conference> findById(Long aLong);



}
