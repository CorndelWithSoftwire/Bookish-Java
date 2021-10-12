package org.softwire.training.bookish.models.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.List;

public interface UserDao {
    @SqlQuery("SELECT u.id as uid, u.forename as uforename, u.surname as usurname, " +
            "c.id, c.book_id, c.borrowed_by, c.return_date " +
            "FROM user u, copy_registry c WHERE u.id = c.borrowed_by")
    @RegisterBeanMapper(value = User.class, prefix = "u")
    @RegisterBeanMapper(value = CopyRegistry.class)
    @UseRowReducer(UserCopyRegistryReducer.class)
    List<User> listLoanUsers();
}
