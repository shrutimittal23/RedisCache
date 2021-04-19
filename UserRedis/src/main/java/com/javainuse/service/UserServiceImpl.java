package com.javainuse.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.javainuse.entity.User;
import com.javainuse.repo.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository itemRepo;

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	@Cacheable(value = "allUsersCache", key = "#id")
	public User getUser(int id) {
		log.info("--- Inside getUserById component ---");
		return itemRepo.getUserById(id);
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "userCache", key = "#id"),
			@CacheEvict(value = "allUsersCache", allEntries = true) })
	public void deleteUser(int id) {
		log.info("--- Inside deleteUser component ---");
		itemRepo.deleteUser(id);
	}

	@Override
	@Caching(put = { @CachePut(value = "userCache", key = "#user.id") }, evict = {
			@CacheEvict(value = "allUsersCache", allEntries = true) })
	public void addUser(User user) {
		log.info("--- Inside addUser component ---");
		itemRepo.addUser(user);
	}

	@Override
	@Caching(put = { @CachePut(value = "userCache", key = "#user.id") }, evict = {
			@CacheEvict(value = "allUsersCache", allEntries = true) })
	public void updateUser(User user) {
		log.info("--- Inside updateUser component ---");
		itemRepo.updateUser(user);
	}

	@Cacheable(value = "allUsersCache", unless = "#result.size() == 0")
	public List<User> getAllUsers() {
		log.info("--- Inside getAllUsers component  ---");
		List<User> list = new ArrayList<>();
		itemRepo.findAll().forEach(e -> list.add(e));
		return list;
	}

}
