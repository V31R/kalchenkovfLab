package kalchenko.Bank.services;

import kalchenko.Bank.entity.User;

public interface UserService {

    public User addUser(User user);
    public User getUser();
    public boolean deleteUser();

}
