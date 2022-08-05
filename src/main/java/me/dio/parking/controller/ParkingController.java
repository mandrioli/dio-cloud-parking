package me.dio.parking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.parking.controller.dto.ParkingDTO;
import me.dio.parking.controller.mapper.ParkingMapper;
import me.dio.parking.model.Parking;
import me.dio.parking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {

	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;

	// dependency injection by Constructor(), replacing @AutoWired 
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper  = parkingMapper;
	}

	@GetMapping
	public List<ParkingDTO> findAll() {
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return result;
	}

}
