package com.ystan.schedule.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
}
