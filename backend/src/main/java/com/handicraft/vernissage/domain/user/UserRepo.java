package com.handicraft.vernissage.domain.user;

import java.util.List;

public interface UserRepo {

    void save(User user);

    List<User> all();

}
