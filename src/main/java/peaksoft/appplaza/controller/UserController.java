package peaksoft.appplaza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza.model.dto.*;
import peaksoft.appplaza.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor // для final uerPerository констуктор тузуп отурбай ушинтип жазып койсо болот
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request) {
        RegistrationResponse response = userService.registration(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/sign-in")
    public LoginResponse login(@RequestBody LoginRequest request){
        return userService.login(request);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping()
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @PutMapping("update/{id}")
    public UserResponse update(@PathVariable("id") Long id, @RequestBody RegistrationRequest request) {
        return userService.update(id, request);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")Long id){
        userService.removeById(id);
        return "User with id: "+id+"succecfully deleted";
    }
}
