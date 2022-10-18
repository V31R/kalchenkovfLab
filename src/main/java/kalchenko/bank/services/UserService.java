package kalchenko.bank.services;

import kalchenko.bank.entity.User;

public interface UserService {

    public User addUser(User user);
    public User getUser();
    public boolean deleteUser();

}
