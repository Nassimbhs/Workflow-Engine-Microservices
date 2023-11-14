package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.ERole;
import workflow.example.workflow.entity.Role;
import static org.junit.Assert.assertNotEquals;

@ExtendWith(MockitoExtension.class)
class RoleEntityTest {

    @Test
    void testRoleEqualsAndHashCode() {
        Role role1 = new Role(1L, ERole.ROLE_USER);
        Role role2 = new Role(1L, ERole.ROLE_ADMIN);

        Assertions.assertEquals(role1, role2);
        Assertions.assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testRoleNotEquals() {
        Role role1 = new Role(1L, ERole.ROLE_USER);
        Role role2 = new Role(2L, ERole.ROLE_ADMIN);

        assertNotEquals(role1, role2);
    }
}
