package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.GroupeUser;
import workflow.example.workflow.entity.User;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class GroupeUserEntityTest {

    @Test
    void testGroupeUserEqualsAndHashCode() {
        GroupeUser groupeUser1 = new GroupeUser(1L, "Group1", "Description1", new HashSet<>());
        GroupeUser groupeUser2 = new GroupeUser(1L, "Group2", "Description2", new HashSet<>());

        Assertions.assertEquals(groupeUser1, groupeUser2);
        Assertions.assertEquals(groupeUser1.hashCode(), groupeUser2.hashCode());
    }

    @Test
    void testGroupeUserNotEquals() {
        GroupeUser groupeUser1 = new GroupeUser(1L, "Group1", "Description1", new HashSet<>());
        GroupeUser groupeUser2 = new GroupeUser(2L, "Group2", "Description2", new HashSet<>());

        Assertions.assertNotEquals(groupeUser1, groupeUser2);
    }

    @Test
    void testGroupeUserUsersRelationship() {
        GroupeUser groupeUser = new GroupeUser();
        User user = mock(User.class);

        Set<User> users = new HashSet<>();
        users.add(user);

        groupeUser.setUsers(users);

        Assertions.assertEquals(users, groupeUser.getUsers());
    }

}
