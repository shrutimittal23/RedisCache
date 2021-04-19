package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.javainuse.entity.User;
import com.javainuse.exception.UserNotFoundException;
import com.javainuse.service.UserServiceImpl;
import java.util.List;

import javax.validation.Valid;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getArticleById(@PathVariable("id") int id) {

		User user = userServiceImpl.getUser(id);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			// throw new UserNotFoundException("User not found", HttpStatus.NOT_FOUND);
			throw new UserNotFoundException("User id '" + id + "' does no exist");
		}

	}

	@PostMapping(value = "/add")
	@ResponseBody
	public ResponseEntity<User> addItem(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		userServiceImpl.addUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<User> updateItem(@Valid @RequestBody User user) {
		if (user != null) {
			userServiceImpl.updateUser(user);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<Void> deleteItem(@PathVariable int id) {
		User user = userServiceImpl.getUser(id);
		if (user != null) {
			userServiceImpl.deleteUser(id);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} else {
			throw new UserNotFoundException("User id '" + id + "' does no exist");
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllArticles() {
		List<User> list = userServiceImpl.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
}
