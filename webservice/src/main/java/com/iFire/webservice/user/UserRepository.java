package com.ifire.webservice.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    User findByActivationToken(String token);

    Page<User> findByIdNot(String id, Pageable page);

    // @Query(value = "Select u from User u")
    // Page<UserProjection> getAllUserRecords(Pageable pageable);

}
