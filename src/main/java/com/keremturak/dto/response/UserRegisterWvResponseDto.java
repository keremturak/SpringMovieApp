package com.keremturak.dto.response;

import com.keremturak.entity.EUserType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.usertype.UserType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserRegisterWvResponseDto {
    Long id;
    String name;
    EUserType userType;
    String email;
    String surname;
}
