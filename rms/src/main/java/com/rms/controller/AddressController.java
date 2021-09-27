package com.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.dto.AddressDto;
import com.rms.entity.Address;
import com.rms.service.AddressService;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping("/addAddress")
	public ResponseEntity<String> addAddress(@RequestBody AddressDto addressDto){
		return new ResponseEntity<>(addressService.addAddress(addressDto),new HttpHeaders(),HttpStatus.OK);	
	}
	
	@GetMapping("/getAddress/{phoneNumber}")
	public ResponseEntity<List<Address>> getAddressByCustomerId(@PathVariable("phoneNumber")Long phoneNumber)
	{
		return new ResponseEntity<>(addressService.getAddressByPhoneNumber(phoneNumber),new HttpHeaders(),HttpStatus.OK);
	}

}
