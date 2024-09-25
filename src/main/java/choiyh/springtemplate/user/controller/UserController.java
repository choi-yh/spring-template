package choiyh.springtemplate.user.controller;

import choiyh.springtemplate.user.dto.LoginRequest;
import choiyh.springtemplate.user.dto.SignUpRequest;
import choiyh.springtemplate.user.dto.UserDTO;
import choiyh.springtemplate.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

        return UserDTO.builder()
                .email(authenticationResponse.getName())
                .build();
    }

    @PostMapping("/signup")
    public UserDTO signup(@RequestBody SignUpRequest request) {
        return userService.save(request);
    }

}
