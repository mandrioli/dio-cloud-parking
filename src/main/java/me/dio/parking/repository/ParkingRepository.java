package me.dio.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.parking.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

}
