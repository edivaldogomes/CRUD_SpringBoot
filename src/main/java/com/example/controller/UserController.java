package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.constants.FacturaConstantes;
import com.example.service.UserService;
import com.example.util.FacturaUtils;

@RestController
@RequestMapping(path="api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> registrarUsuario(@RequestBody(required=true) Map<String, String> requestMap) {
		try {
			return userService.signUp(requestMap);			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
