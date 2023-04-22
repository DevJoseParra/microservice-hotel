package com.joseparra.hotelmicroservice.restcontrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joseparra.hotelmicroservice.dtos.HotelDto;
import com.joseparra.hotelmicroservice.services.IHotelService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/hotel/v1")
@Slf4j
public class HotelRestController {
	@Autowired
	private IHotelService hotelService;

	@PostMapping(value = "/create")
	public ResponseEntity<?> createHotel(@Valid @RequestBody HotelDto hotelDto, BindingResult bindingResult) {
		log.info("--CREATING HOTEL--");
		if (bindingResult.hasErrors()) {
			StringBuilder messageErrors = this.messageErrors(bindingResult);
			log.error(messageErrors.toString());
			return new ResponseEntity<>(messageErrors, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotelDto));
	}

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllHotels() {
		log.info("--LISTING HOTELS--");
		List<HotelDto> listHotel = hotelService.findAllHotel();
		if (listHotel.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are't hotels.");
		}
		return ResponseEntity.ok(listHotel);
	}

	@GetMapping(value = "/id/{idHotel}")
	public ResponseEntity<HotelDto> getHotelById(@PathVariable("idHotel") Long idHotel) {
		log.info("--GETTING HOTEL BY ID--");
		return ResponseEntity.ok(hotelService.findByIdHotel(idHotel));
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateHotel(@Valid @RequestBody HotelDto hotelDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder messageErrors = this.messageErrors(bindingResult);
			log.error(messageErrors.toString());
			return new ResponseEntity<>(messageErrors, HttpStatus.BAD_REQUEST);
		}
		HotelDto hotelDtoUpdate = hotelService.updateHotel(hotelDto);
		log.info("Updated hotel with id=" + hotelDto.getId());
		return ResponseEntity.ok(hotelDtoUpdate);
	}

	@DeleteMapping("/delete/{idHotel}")
	public ResponseEntity<HttpStatus> deleteHotel(@PathVariable("idHotel") Long idHotel) {
		hotelService.deleteByIdHotel(idHotel);
		log.info("Deleted hotel with id=" + idHotel);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private StringBuilder messageErrors(BindingResult bindingResult) {
		StringBuilder messageErrors = new StringBuilder("It can't register/update to the hotel. The hotel has "
				+ bindingResult.getFieldErrorCount() + " errors. ");
		List<ObjectError> listErrors = bindingResult.getAllErrors();
		int numberMessage = 1;
		for (ObjectError objectError : listErrors) {
			if (numberMessage < bindingResult.getFieldErrorCount()) {
				messageErrors.append(objectError.getDefaultMessage() + ", ");
			} else {
				messageErrors.append(objectError.getDefaultMessage() + ".");
			}
			numberMessage += 1;
		}
		return messageErrors;
	}

}
