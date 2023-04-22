package com.joseparra.hotelmicroservice.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hotels")
public class Hotel extends AbstractEntityPersistentObject {

	@NotBlank(message = "Name can't be blank")
	private String name;
	@NotBlank(message = "Information can't be blank")
	private String information;
	@NotBlank(message = "addrees can't be blank")
	private String addrees;
}
