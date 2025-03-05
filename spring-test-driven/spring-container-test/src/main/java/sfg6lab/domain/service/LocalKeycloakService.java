//: sfg6lab.domain.service.LocalKeycloakService.java

package sfg6lab.domain.service;


import java.net.URI;


public interface LocalKeycloakService {

    URI localConfigUri();

    static LocalKeycloakService localKeycloakService() {
        return new LocalKeycloakServiceImpl();
    }
}

final class LocalKeycloakServiceImpl implements LocalKeycloakService {

    static final String LOCAL_KEYCLOAK_LOCAL_CONFIG_UTL =
            "http://keycloak.local/realms/testops/well-known/openid-configuration";

    @Override
    public URI localConfigUri() {
        return URI.create(LOCAL_KEYCLOAK_LOCAL_CONFIG_UTL);
    }

}
