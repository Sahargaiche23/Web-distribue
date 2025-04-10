package com.sncft.train.commonApi.commands;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class CreateAccountCommand extends baseCommand<String> {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String firstname;
    @Getter
    @Setter
    private String lastname;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String photo;





    public CreateAccountCommand (String id, String username,String firstname, String lastname, String email ,String password,String photo)

    {
        super(id);
        this.username= username;
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.password=password;
        this.photo=photo;


    }



}

