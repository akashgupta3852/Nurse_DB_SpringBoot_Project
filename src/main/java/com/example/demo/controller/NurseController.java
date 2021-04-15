package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NurseDTO;
import com.example.demo.model.Nurse;
import com.example.demo.service.NurseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/nurses")
public class NurseController {

	@Autowired
	private NurseService nurseService;

	@GetMapping("/list")
	@ApiOperation(value = "Find all the nurse details from database", notes = "null", response = Nurse.class)
	public List<Nurse> getNurses() {
		return nurseService.getNurses();
	}

	// @ApiParam is used to provide the extra notes to the Api parameter
	@GetMapping("/list/id/{nurseId}")
	@ApiOperation(value = "Find the Nurse's data by Id", notes = "Provide an id to look up specific nurse from the nurse database", response = Nurse.class)
	public Optional<Nurse> getNurseDataById(
			@ApiParam(value = "Id value for the nurse you need to retrieve", required = true) @PathVariable("nurseId") int nurseId) {
		return nurseService.getNurseDataById(nurseId);
	}

	@GetMapping("/list/lastname/{lastName}")
	@ApiOperation(value = "Find the list of nurses by LastName", notes = "null", response = Nurse.class)
	public List<Nurse> getNursesByLastName(@PathVariable("lastName") String lastName) {
		return nurseService.getNursesByLastName(lastName);
	}

	@GetMapping("/list/email/{email:.+}")
	@ApiOperation(value = "Find the list of nurses by email", notes = "null", response = Nurse.class)
	public List<Nurse> getNursesByEmail(@PathVariable("email") String email) {
		return nurseService.getNursesByEmail(email);
	}

	@PostMapping("/create")
	@ApiOperation(value = "Add nurse data in database", notes = "Nurse data will be added in the database", response = String.class)
	public String addNurseToDb(@RequestBody NurseDTO nurseDTO) {
		nurseService.addNurseToDB(nurseDTO);
		return "Nurse name: " + nurseDTO.getFirstName() + " " + nurseDTO.getLastName() + " has been added";
	}

	@PutMapping("/update/{nurseId}")
	@ApiOperation(value = "Update the Nurse's data by Id", notes = "Provide an id to update the nurse details in the nurse database", response = String.class)
	public String updateNurseData(@PathVariable("nurseId") int nurseId, @RequestBody NurseDTO nurseDTO) {
		return nurseService.updateNurseData(nurseId, nurseDTO);
	}

	@PutMapping("/update/{nurseId}/{nurseEmail:.+}")
	@ApiOperation(value = "Update the Nurse's email by Id", notes = "Provide an id to update the nurse's email in the nurse database", response = String.class)
	public String updateNurseEmail(@PathVariable("nurseId") int nurseId,
			@PathVariable("nurseEmail") String nurseEmail) {
		return nurseService.updateNurseEmail(nurseId, nurseEmail);
	}

	@DeleteMapping("/delete/{nurseId}")
	@ApiOperation(value = "Delete the Nurse's data by Id", notes = "Provide an id to delete the specific nurse's details from the nurse database", response = String.class)
	public String deleteNurseData(@PathVariable("nurseId") int nurseId) {
		return nurseService.deleteNurseData(nurseId);
	}

	@GetMapping("/primeNumber/{startingNumber}/{count}")
	@ApiOperation(value = "Find n prime numbers after a given number k", notes = "This api will give the n prime numbers after a given number k", response = String.class)
	public String getPrimeNumbers(@PathVariable("startingNumber") int startingNumber,
			@PathVariable("count") int count) {
		return nurseService.getPrimeNumbers(startingNumber, count);
	}

	@PostMapping("/pairSum/{sumNumber}")
	@ApiOperation(value = "Find the pair of two number whose sum is the given number", notes = "This api will give all the pairs", response = String.class)
	public String getPairNumbers(@PathVariable("sumNumber") int sumNumber, @RequestBody int[] arr) {
		return nurseService.getPairNumbers(sumNumber, arr);
	}

	@PostMapping("/reqMessage")
	public String isSizeVaild(@RequestBody String request) throws UnsupportedEncodingException {
		return nurseService.isSizeVaild(request);
	}

	@GetMapping("/html")
	public String get() {
		return "index.jsp";
	}

}
