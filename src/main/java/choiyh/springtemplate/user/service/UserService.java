package choiyh.springtemplate.user.service;

import choiyh.springtemplate.user.dto.SignUpRequest;
import choiyh.springtemplate.user.dto.UserDTO;
import choiyh.springtemplate.user.entity.User;
import choiyh.springtemplate.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO save(SignUpRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return UserDTO.of(user);
    }

}
