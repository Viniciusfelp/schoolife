package br.ufpe.cin.aps.authservice.services;

import br.ufpe.cin.aps.authservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class CustomOidcUserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;


    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = (OidcUser) super.loadUser(userRequest);

        // Aqui você pode obter informações do usuário, como e-mail e ID do Google, e armazená-las no seu repositório de usuários.
        String email = oidcUser.getEmail();
        String googleId = oidcUser.getSubject();

        // ...

        return oidcUser;
    }
}
