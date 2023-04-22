package com.joseparra.hotelmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joseparra.hotelmicroservice.entities.Hotel;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Long> {

}
