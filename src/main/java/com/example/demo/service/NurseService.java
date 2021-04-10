package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Nurse;
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

	public Nurse addNurse(Nurse nurse) {
		return nurseRepository.save(nurse);
	}

	public String updateNurseData(int nurseId, Nurse nurseData) {
		Nurse nurse = new Nurse();
		Optional<Nurse> optional = this.getNurseDataById(nurseId);
		if (optional.isPresent()) {
			nurseRepository.deleteById(nurseId);
			nurse.updateNurseData(nurseId, nurseData);
			nurseRepository.save(nurse);
			return "Nurse name: " + nurseData.getName() + "'s data has been updated";
		}
		return "Nurse name: " + nurseData.getName() + "'s data is not available in the database";
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

}

//Nurse n1 = new Nurse();
//n1.setId(1);
//n1.setName("Priya");
//n1.setAge(32);
//n1.setSalary(58000);
//
//Nurse n2 = new Nurse();
//n2.setId(2);
//n2.setName("Supriya");
//n2.setAge(31);
//n2.setSalary(60000);
//
//nurseList.add(n1);
//nurseList.add(n2);

//private List<Nurse> nurseList = new ArrayList<>();;
//
//public List<Nurse> getNurses() {
//	return nurseList;
//}
//
//public Nurse getNurseData(int nurseId) {
//	for (Nurse nurse : nurseList) {
//		if (nurse.getId() == nurseId) {
//			return nurse;
//		}
//	}
//	return null;
//}
//
//public String addNurse(Nurse nurse) {
//	nurseList.add(nurse);
//	return "Nurse name: " + nurse.getName() + " has been added";
//}
//
//public String updateNurseData(int nurseId, Nurse nurseData) {
//	for (Nurse nurse : nurseList) {
//		if (nurse.getId() == nurseId) {
//			int index = nurseList.indexOf(nurse);
//			nurseList.set(index, nurseData);
//		}
//	}
//	return "Nurse name: " + nurseData.getName() + " has been updated";
//}
//
//public String deleteNurseData(int nurseId) {
//	Nurse nurseToBeDelete = new Nurse();
//	for (Nurse nurse : nurseList) {
//		if (nurse.getId() == nurseId) {
//			nurseToBeDelete = nurse;
//			break;
//		}
//	}
//	nurseList.remove(nurseToBeDelete);
//	return "Nurse with id: " + nurseId + " has been added";
//}
