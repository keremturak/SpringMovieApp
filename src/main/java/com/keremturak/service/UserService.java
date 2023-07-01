package com.keremturak.service;

import com.keremturak.Repository.IUserRepository;
import com.keremturak.dto.request.LoginUserRequestDto;
import com.keremturak.dto.request.RegisterUserRequestDto;
import com.keremturak.dto.response.UserFindAllResponseDto;
import com.keremturak.dto.response.UserLoginResponseDto;
import com.keremturak.dto.response.UserRegisterWvResponseDto;
import com.keremturak.entity.EUserType;
import com.keremturak.entity.User;
import com.keremturak.exception.ErrorType;
import com.keremturak.exception.MovieAppException;
import com.keremturak.mapper.IUserMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.usertype.UserType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository repository;

    public User createUser(String name, String surname, String email, String password, String userType) {
        EUserType userType1 =null;
        User user;
        try {
            userType1 = EUserType.valueOf(userType);
            user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .userType(userType1)
                    .build();

        }catch (Exception e) {
            user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build();
        }return repository.save(user);

    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void saveAll(List<User> user1) {
        repository.saveAll(user1);
    }
    public List<User> findByName() {
        return repository.findAllByOrderByName();
    }

    public List<User> findAllByNameContaining(String name) {
        return repository.findAllByNameContaining(name);
    }

    public List<User> findAllByEmailContainingIgnoreCase(String email) {
        return repository.findAllByEmailContainingIgnoreCase(email);
    }

    public List<User> findAllByEmailEndingWith(String email) {
        return repository.findAllByEmailEndingWith(email);
    }

    public Boolean existsByEmailAndPassword(String email, String password) {
        return repository.existsByEmailAndPassword(email, password);
    }

    public User findByEmailAndPassword(String email, String password) {
        if (repository.findByEmailAndPassword(email, password).isPresent()) {
            return repository.findByEmailAndPassword(email, password).get();
        }else {
            throw new MovieAppException(ErrorType.USER_NOT_FOUND);
        }


    }
    public Optional<User> findById(Long id){
        if (repository.findById(id).isPresent()) {
            return repository.findById(id);
        }else
            throw new RuntimeException("Kullanıcı Bulunamadı");

    }

    public UserRegisterWvResponseDto register(RegisterUserRequestDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        User user2 =repository.save(user);
        UserRegisterWvResponseDto dto2= UserRegisterWvResponseDto.builder()
                .name(user2.getName())
                .surname(user2.getSurname())
                .email(user2.getEmail())
                .id(user2.getId())
                .userType(user2.getUserType())
                .build();
        return dto2;

    }

    public  List<UserFindAllResponseDto> findAllDto() {
        List<User> users= repository.findAll();
        List<UserFindAllResponseDto> dtos =new ArrayList<>();
        users.forEach(x->{
            UserFindAllResponseDto dto = UserFindAllResponseDto.builder()
                    .name(x.getName())
                    .email(x.getEmail())
                    .id(x.getId())
                    .surname(x.getSurname())
                    .phone(x.getPhone())
                    .userType(x.getUserType())
                    .favGenre(x.getFavGenre())
                    .favmovies(x.getFavMovie())
                    .movieContent(x.getComments().stream().collect(Collectors.toMap(z-> z.getMovie().getName(),t-> t.getContent())))
                    .movieComments(x.getComments().stream().map(y -> y.getContent()).collect(Collectors.toList()))
                    .build();
            dtos.add(dto);
        });
        return dtos;

    }
    public RegisterUserRequestDto register2(@RequestBody UserRegisterWvResponseDto dto){
        User user = IUserMapper.INSTANCE.UserFromDto(dto);

        return IUserMapper.INSTANCE.DtoFromUser(repository.save(user));
    }

    public UserLoginResponseDto login(LoginUserRequestDto dto) {

        if (repository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()).isPresent()) {
            return IUserMapper.INSTANCE.dtoFromUserLogin(repository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()).get());

        }else {
            throw new RuntimeException("Couldn't find");
        }

    }
}
