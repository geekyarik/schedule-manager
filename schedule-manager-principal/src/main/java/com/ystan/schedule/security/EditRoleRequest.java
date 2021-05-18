package com.ystan.schedule.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditRoleRequest {

    private String username;
    private String roleId;
    private boolean enable;
}
