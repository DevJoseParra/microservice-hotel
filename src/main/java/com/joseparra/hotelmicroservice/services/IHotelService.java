package com.joseparra.hotelmicroservice.services;

import java.util.List;

import com.joseparra.hotelmicroservice.dtos.HotelDto;

public interface IHotelService {

	public List<HotelDto> findAllHotel();

	public HotelDto createHotel(HotelDto hotel);

	public HotelDto updateHotel(HotelDto hotel);

	public HotelDto findByIdHotel(Long id);

	public void deleteByIdHotel(Long id);

}
