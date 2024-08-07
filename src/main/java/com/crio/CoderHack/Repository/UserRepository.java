package com.crio.CoderHack.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.CoderHack.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
}