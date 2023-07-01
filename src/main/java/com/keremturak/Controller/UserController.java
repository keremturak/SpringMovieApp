package com.keremturak.Controller;

import com.keremturak.dto.request.LoginUserRequestDto;
import com.keremturak.dto.request.RegisterUserRequestDto;
import com.keremturak.dto.response.UserFindAllResponseDto;
import com.keremturak.dto.response.UserLoginResponseDto;
import com.keremturak.dto.response.UserRegisterWvResponseDto;
import com.keremturak.entity.EUserType;
import com.keremturak.entity.User;
import com.keremturak.mapper.IUserMapper;
import com.keremturak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.usertype.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/create")
    public User createUser(String name, String surname, String email, String password, String userType) {
        return userService.createUser(name, surname, email, password, userType);
    }
    @GetMapping("/findall")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findbyname")
    public ResponseEntity<List<User>> findByName(){
        return ResponseEntity.ok(userService.findByName());
    }
    @GetMapping("/findbyuserindex")
    public ResponseEntity<List<User>> findByUserIndex(String name){
        return ResponseEntity.ok(userService.findAllByNameContaining(name));
    }
    @GetMapping("/findbyemailindex")
    public List<User> findByEmailIndex(String email){
        return userService.findAllByEmailContainingIgnoreCase(email);
    }
    @GetMapping("/findallbyemailendingwith")
    public List<User> findAllByEmailEndingWith(String email) {
        return userService.findAllByEmailEndingWith(email);
    }
    @GetMapping("/logintruefalse")
    public ResponseEntity<Boolean> existsByEmailAndPassword(String email, String password) {
        return ResponseEntity.ok(userService.existsByEmailAndPassword(email, password));
    }
    @GetMapping("/login")
    public User findByEmailAndPassword(String email, String password) {
        return userService.findByEmailAndPassword(email, password);
    }
    @PostMapping("/register")
    public UserRegisterWvResponseDto register(@RequestBody RegisterUserRequestDto dto){
        return userService.register(dto);
    }

    @GetMapping("/findalldto")
    public  List<UserFindAllResponseDto> findAllDto(){
        return userService.findAllDto();
    }

    @PostMapping("/register2")
    public RegisterUserRequestDto register2(@RequestBody UserRegisterWvResponseDto dto){
        return userService.register2(dto);
    }

    @PostMapping("/login2")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody LoginUserRequestDto dto){
        return new ResponseEntity(userService.login(dto), HttpStatus.CREATED);
    }



}
