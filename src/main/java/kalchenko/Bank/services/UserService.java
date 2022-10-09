package kalchenko.Bank.services;

import kalchenko.Bank.entity.User;

public interface UserService {

    public User addUser();
    public User getUser();
    public boolean deleteUser();

}
