package com.example.service.implementation;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.constants.FacturaConstantes;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.util.FacturaUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		//log.info("Registro interno de un usuario {}", requestMap);
		try {
			if (validateSignUpMap(requestMap)) {
				User user = userRepository.findByEmail(requestMap.get("email"));
				if (Objects.isNull(user)) {
					userRepository.save(getUserFromMapping(requestMap));
					return FacturaUtils.getResponseEntity("Usuario registrado con éxito", HttpStatus.CREATED);
				} else {
					return FacturaUtils.getResponseEntity("El usuario con ese email ya existe", HttpStatus.BAD_REQUEST);
				}
			} else {
				return FacturaUtils.getResponseEntity("Datos de registro incompletos", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean validateSignUpMap(Map<String, String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
				&& requestMap.containsKey("password")) {
			return true;
		}
		return false;
	}

	private User getUserFromMapping(Map<String, String> requestMap) {
		User user = new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setStatus("false");
		user.setRole("user");
		return user;
	}
}
