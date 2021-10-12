package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.User;
import java.util.List;

public class AdminPageModel {
    private List<User> users;
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
