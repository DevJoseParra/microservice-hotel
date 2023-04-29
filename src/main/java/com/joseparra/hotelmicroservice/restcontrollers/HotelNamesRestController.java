package com.joseparra.hotelmicroservice.restcontrollers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joseparra.hotelmicroservice.dtos.HotelDto;
import com.joseparra.hotelmicroservice.services.IHotelService;

@RestController
@RequestMapping(value = "/api/hotelnames/v1")
public class HotelNamesRestController {

	@Autowired
	private IHotelService hotelService;

	@GetMapping(value = "/names")
	public ResponseEntity<?> getListHotelNames() {
		List<HotelDto> listHotel = hotelService.findAllHotel();
		if (listHotel.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are't hotels.");
		}
		return ResponseEntity.ok(listHotel.stream().map(HotelDto::getName).collect(Collectors.toList()));
	}
}
