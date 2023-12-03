package peaksoft.appplaza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.appplaza.mapper.UserMapper;
import peaksoft.appplaza.model.dto.RegistrationRequest;
import peaksoft.appplaza.model.dto.RegistrationResponse;
import peaksoft.appplaza.model.dto.UserResponse;
import peaksoft.appplaza.model.entities.Role;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.repository.RoleRepository;
import peaksoft.appplaza.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // для final uerPerository констуктор тузуп отурбай ушинтип жазып койсо болот
public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

//    @Autowired
//    public UserService(UserRepository repository, UserMapper userMapper) {  @RequiredArgsConstructor ушинтип жазсак башына
//        this.repository = repository;               конструктор тузуп @Autowired кылыштын кереги жок болот
//        this.userMapper = userMapper;
//    }

    public RegistrationResponse registration(RegistrationRequest request) {
        User user = userMapper.mapToEntity(request);
        if (user.getName().length() < 2 || user.getLastName().length() < 2) {
            throw new RuntimeException("User's name or last name must be more 2 simbol ! ");
        }
        if(!user.getEmail().contains("@")){
            throw new RuntimeException("your email don't have ' @ '");
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = new ArrayList<>();
        if (repository.findAll().isEmpty()) {
            Role role = roleRepository.findByName("ADMIN");
            if (role == null) {
                role = new Role("ADMIN");
            }
            roles.add(role);

        } else {
            Role role = roleRepository.findByName("USER");
            if (role == null) {
                role = new Role("USER");
            }
            roles.add(role);
        }
        user.setRoles(roles);
        repository.save(user);
        return userMapper.mapToResponse(user);

    }

    public UserResponse findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found by id:" + id));
        return userMapper.mapToUserResponse(user);
    }


    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(userMapper::mapToUserResponse).toList();
    }

    public UserResponse update(Long userId, RegistrationRequest request) {
        User oldUser = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found by id:" + userId));
        oldUser.setName(request.getName());
        oldUser.setLastName(request.getLastName());
        oldUser.setEmail(request.getEmail());
        oldUser.setAge(request.getAge());
        oldUser.setSubscribe(request.isSubscribe());
        repository.save(oldUser);
        return userMapper.mapToUserResponse(oldUser);
    }

    public void removeById(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found by id:" + userId));
        repository.delete(user);
    }

}
