package roman.pidkostelnyi.victoriaarmario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import roman.pidkostelnyi.victoriaarmario.dto.request.UserRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.AuthenticationResponse;
import roman.pidkostelnyi.victoriaarmario.service.UserDetailService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody UserRequest request) {
        return userDetailService.login(request);
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@Valid @RequestBody UserRequest request) {
        return userDetailService.register(request);
    }

    @GetMapping("/checkToken")
    public void checkToken() {
    }

    @PostMapping("/secured")
    public void test() {

    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/secured1")
    public void test1() {

    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/secured2")
    public void test2() {

    }
}
