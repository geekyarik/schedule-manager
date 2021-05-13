package com.ystan.schedule.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AuthResponse {

    @Getter
    private final String jwt;
}
