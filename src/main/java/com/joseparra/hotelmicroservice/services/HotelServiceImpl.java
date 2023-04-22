package com.joseparra.hotelmicroservice.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.joseparra.hotelmicroservice.dtos.HotelDto;
import com.joseparra.hotelmicroservice.entities.Hotel;
import com.joseparra.hotelmicroservice.exceptions.BadRequestException;
import com.joseparra.hotelmicroservice.exceptions.ResourceNotFoundException;
import com.joseparra.hotelmicroservice.repositories.IHotelRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements IHotelService {

	private final IHotelRepository hotelRepository;

	private final ModelMapper modelMapper;

	@Override
	public List<HotelDto> findAllHotel() {
		List<Hotel> listHotels = hotelRepository.findAll();
		return listHotels.stream().map(hotel -> modelMapper.map(hotel, HotelDto.class)).collect(Collectors.toList());
	}

	@Override
	public HotelDto createHotel(HotelDto hotelDto) {
		Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
		return modelMapper.map(hotelRepository.save(hotel), HotelDto.class);
	}

	@Override
	public HotelDto updateHotel(HotelDto hotelDto) throws ResourceNotFoundException, BadRequestException {
		Long idHotelUpdate = hotelDto.getId();
		Hotel updateHotel = null;
		if (idHotelUpdate != null) {
			Optional<Hotel> optionalHotel = hotelRepository.findById(idHotelUpdate);
			if (!optionalHotel.isPresent()) {
				throw new ResourceNotFoundException("User", "ID", idHotelUpdate.toString());
			} else {
				updateHotel = optionalHotel.get();
				updateHotel.setName(hotelDto.getName());
				updateHotel.setInformation(hotelDto.getInformation());
				updateHotel.setAddrees(hotelDto.getAddrees());
			}
		} else {
			throw new BadRequestException("id " + idHotelUpdate);
		}
		return modelMapper.map(hotelRepository.save(updateHotel), HotelDto.class);
	}

	@Override
	public HotelDto findByIdHotel(Long id) throws ResourceNotFoundException {
		Optional<Hotel> optionalHotel = hotelRepository.findById(id);
		return modelMapper.map(
				optionalHotel.orElseThrow(() -> new ResourceNotFoundException("User", "ID", id.toString())),
				HotelDto.class);
	}

	@Override
	public void deleteByIdHotel(Long id) throws ResourceNotFoundException {
		Optional<Hotel> optionalHotel = hotelRepository.findById(id);
		optionalHotel.ifPresentOrElse(hotel -> hotelRepository.deleteById(hotel.getId()), () -> {
			throw new ResourceNotFoundException("User", "ID", id.toString());
		});
	}

}
