package com.example.SpringTrySample.form;

import lombok.Data;

@Data
public class UserReceiveForm {
    private Integer id;
    private String username;
    private String password;
    private String authority;

}
