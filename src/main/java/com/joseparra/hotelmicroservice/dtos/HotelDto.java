package com.joseparra.hotelmicroservice.dtos;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {

	private Long id;
	@NotBlank(message = "Name can't be blank")
	private String name;
	@NotBlank(message = "Information can't be blank")
	private String information;
	@NotBlank(message = "addrees can't be blank")
	private String addrees;
}
