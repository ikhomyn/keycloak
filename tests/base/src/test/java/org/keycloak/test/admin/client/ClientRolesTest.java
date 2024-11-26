package org.keycloak.test.admin.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.events.admin.OperationType;
import org.keycloak.events.admin.ResourceType;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.test.framework.annotations.InjectAdminEvents;
import org.keycloak.test.framework.annotations.InjectClient;
import org.keycloak.test.framework.annotations.KeycloakIntegrationTest;
import org.keycloak.test.framework.events.AdminEvents;
import org.keycloak.test.framework.realm.ClientConfig;
import org.keycloak.test.framework.realm.ClientConfigBuilder;
import org.keycloak.test.framework.realm.ManagedClient;
import org.keycloak.test.framework.server.KeycloakTestServerConfig;

import java.util.Collections;


@KeycloakIntegrationTest
public class ClientRolesTest {

    @InjectAdminEvents
    AdminEvents adminEvents;

    @InjectClient//(config = OidcClient.class)
    ManagedClient client;

    /*
    *   ClientRepresentation clientRep = new ClientRepresentation();
        clientRep.setClientId(name);
        clientRep.setName(name);
        clientRep.setProtocol("openid-connect");
    *
    * */

    @Test
    public void testAddRole() {
        RoleRepresentation role1 = makeRole("role1");
        role1.setDescription("role1-description");
        role1.setAttributes(Collections.singletonMap("role1-attr-key", Collections.singletonList("role1-attr-val")));
        client.admin().roles().create(role1);

        Assertions.assertEquals(OperationType.CREATE, adminEvents.poll().getOperationType());
        //adminEvents.poll().getResourcePath()
        Assertions.assertEquals(ResourceType.CLIENT_ROLE, adminEvents.poll().getResourceType());
        //Assertions.assertEquals(RepresentationUtils, adminEvents.poll().getRepresentation());
        //adminEvents.poll().getRepresentation()
        //assertAdminEvents.assertEvent(getRealmId(), OperationType.CREATE, AdminEventPaths.clientRoleResourcePath(clientDbId, "role1"), role1, ResourceType.CLIENT_ROLE);

    }

    @Test
    public void createRoleWithSameName() {

    }

    @Test
    public void createRoleWithNamePattern() {

    }

    @Test
    public void testRemoveRole() {

    }

    @Test
    public void testComposites() {

    }

    @Test
    public void testCompositeRolesSearch() {

    }

    @Test
    public void usersInRole() {

    }

    @Test
    public void testSearchForRoles() {

    }

    @Test
    public void testPaginationRoles() {

    }

    @Test
    public void testPaginationRolesCache() {

    }

    @Test
    public void getRolesWithFullRepresentation() {

    }

    @Test
    public void getRolesWithBriefRepresentation() {

    }

    private RoleRepresentation makeRole(String name) {
        RoleRepresentation role = new RoleRepresentation();
        role.setName(name);
        return role;
    }

    private boolean hasRole(RolesResource rolesRsc, String name) {
        for (RoleRepresentation role : rolesRsc.list()) {
            if (role.getName().equals(name)) return true;
        }

        return false;
    }

    static class OidcClient implements ClientConfig {

        @Override
        public ClientConfigBuilder configure(ClientConfigBuilder client) {
            return client.clientId("roleClient")
                    .setProtocol("openid-connect");

        }
    }

    public static class ServerConfig implements KeycloakTestServerConfig {
        @Override
        public boolean enableSysLog() {
            return true;
        }
    }

}
