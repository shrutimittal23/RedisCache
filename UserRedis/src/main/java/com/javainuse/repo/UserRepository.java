package com.javainuse.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.javainuse.entity.User;

import java.util.List;

@Repository
public class UserRepository {

	public static final String KEY = "USER";
	private RedisTemplate<String, User> redisTemplate;
	private HashOperations hashOperations;
	private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

	public UserRepository(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}

	public List<User> findAll() {
		return hashOperations.values(KEY);
	}

	/* Getting a specific item by item id from table */
	public User getUserById(int id) {
		return (User) hashOperations.get(KEY, id);
	}

	/* Adding an item into redis database */
	public void addUser(User user) {
		hashOperations.put(KEY, user.getId(), user);
	}

	/* delete an item from database */
	public void deleteUser(int id) {
		hashOperations.delete(KEY, id);
	}

	/* update an item from database */
	public void updateUser(User user) {
		addUser(user);
	}
}
