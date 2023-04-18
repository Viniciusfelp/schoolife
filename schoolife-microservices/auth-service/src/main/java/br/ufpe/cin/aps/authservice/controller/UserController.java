package br.ufpe.cin.aps.authservice.controller;

import br.ufpe.cin.aps.authservice.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/user")
    public ResponseEntity<?> user(@AuthenticationPrincipal OAuth2User principal, Authentication authentication) {
        String email = principal.getAttribute("email");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> userTypeResponse = restTemplate.getForEntity("http://academic-service-host/api/user-type?email=" + email, Map.class);

        if (userTypeResponse.getStatusCode().is2xxSuccessful()) {
            String userType = (String) userTypeResponse.getBody().get("userType");
            String jwtToken = jwtTokenUtil.generateToken(authentication);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("email", email);
            responseBody.put("name", principal.getAttribute("name"));
            responseBody.put("userType", userType);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Authorization", "Bearer " + jwtToken);

            return ResponseEntity.ok().headers(responseHeaders).body(responseBody);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}


