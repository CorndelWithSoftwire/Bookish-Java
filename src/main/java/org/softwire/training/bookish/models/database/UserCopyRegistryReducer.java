package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

import java.util.Map;

public class UserCopyRegistryReducer
        implements LinkedHashMapRowReducer<Integer, User> {
    @Override
    public void accumulate(final Map<Integer, User> map,
                           final RowView rowView) {

        final User user = rowView.getRow(User.class);

        final User userLoans
                = map.computeIfAbsent(user.getId(),
                id -> user);

        userLoans.addCopyRegistry(rowView.getRow(CopyRegistry.class));
    }
}

