package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.NurseDTO;
import com.example.demo.model.Nurse;
//import com.example.demo.repository.NurseAddressRepository;
import com.example.demo.repository.NurseRepository;

@Service
public class NurseService {

	@Autowired
	private NurseRepository nurseRepository;

	public List<Nurse> getNurses() {
		return (List<Nurse>) nurseRepository.findAll();
	}

	public Optional<Nurse> getNurseDataById(int nurseId) {
		return nurseRepository.findById(nurseId);
	}

	public List<Nurse> getNursesByLastName(String lastName) {
		lastName = lastName.toLowerCase();
		return nurseRepository.findByLastName(lastName);
	}

	public List<Nurse> getNursesByEmail(String email) {
		email = email.toLowerCase();
		return nurseRepository.findByEmail(email);
	}

	public Nurse addNurse(NurseDTO nurseDTO) {
		Nurse nurse = new Nurse(nurseDTO);
		return nurseRepository.save(nurse);
	}

	public String updateNurseData(int nurseId, NurseDTO nurseDTO) {
		Nurse nurse = new Nurse();
		Optional<Nurse> optional = this.getNurseDataById(nurseId);
		if (optional.isPresent()) {
			nurse = nurse.updateNurseData(nurseId, nurseDTO);
			nurseRepository.save(nurse);
			return "Nurse name: " + nurseDTO.getFirstName() + " " + nurseDTO.getLastName() + "'s data has been updated";
		}
		return "Nurse name: " + nurseDTO.getFirstName() + " " + nurseDTO.getLastName()
				+ "'s data is not available in the database";
	}

	public String updateNurseEmail(int nurseId, String nurseEmail) {
		Optional<Nurse> optional = this.getNurseDataById(nurseId);
		if (optional.isPresent()) {
			Nurse nurse = optional.get();
			nurse.setEmail(nurseEmail);
			nurseRepository.save(nurse);
			return "Nurse name: " + nurse.getFirstName() + " " + nurse.getLastName() + "'s email has been updated";
		}
		return "Nurse id: " + nurseId + " is not available in the database";
	}

	public String deleteNurseData(int nurseId) {
		if (!isPresent(nurseId))
			return "Nurse with id: " + nurseId + " is not available in the database";
		nurseRepository.deleteById(nurseId);
		return "Nurse with id: " + nurseId + " has been deleted";
	}

	public boolean isPresent(int nurseId) {
		Optional<Nurse> nurseData = nurseRepository.findById(nurseId);
		if (nurseData.isPresent())
			return true;
		return false;
	}

	public String getPrimeNumbers(int startingNumber, int count) {
		int num = startingNumber + 1, counts = count;
		List<Integer> primeNumbers = new ArrayList<>();
		while (counts > 0) {
			int temp;
			boolean isPrime = true;
			for (int i = 2; i <= num / 2; i++) {
				temp = num % i;
				if (temp == 0) {
					isPrime = false;
					break;
				}
			}
			// If isPrime is true then the number is prime else not
			if (isPrime) {
				System.out.println(num);
				primeNumbers.add(num);
				counts--;
			}
			num++;
		}
		return "The list of  first " + count + " prime numbers after " + startingNumber + " are " + primeNumbers;
	}

	public String getPairNumbers(int sumNumber, int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			int key = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				int value = arr[j];
				if (key + value == sumNumber) {
					if (map.keySet().contains(value))
						continue;
					map.put(key, value);
					break;
				}
			}
		}

		if (map.size() == 0)
			return ("There is no such pair of numbers whose sum is " + sumNumber + ".");
		String str = "There is " + map.size() + " pair(s) whose sum is " + sumNumber + ".\n";
		str = str + ("The list of pair(s) is(are): ");
		for (Map.Entry<Integer, Integer> mapEntry : map.entrySet()) {
			str = str + "\n(" + mapEntry.getKey() + "," + mapEntry.getValue() + ")";
		}
		return str;
	}

	public String isSizeVaild(String message) throws UnsupportedEncodingException {
		byte[] bytes = message.getBytes("UTF-8");
		int noOfBytes = bytes.length;
		String str = "Number of bytes in the search request message is " + noOfBytes + ", so the request is ";
		if (noOfBytes <= (4 * 1024))
			return str + "permissible. You can go to the next step.";
		return str + "not permissible.";
	}

}