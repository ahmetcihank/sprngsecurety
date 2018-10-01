package springsecurety.service;

import springsecurety.model.User;

/**
 * Created by SOFTWARE02 on 19.08.2018.
 */
public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);
}