package org.softwire.training.bookish.restService.services;

import org.softwire.training.bookish.models.database.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository<User,Integer> {
}
