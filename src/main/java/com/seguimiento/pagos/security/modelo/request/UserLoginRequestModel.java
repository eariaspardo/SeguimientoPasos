package com.seguimiento.pagos.security.modelo.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestModel {

    @NotEmpty
    @Email
    private String nombreUsuario;

    @NotEmpty
    private String password;

}
