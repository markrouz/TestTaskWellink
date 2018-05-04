package com.mgerman;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("session")
public class MainBean {

    private String hellString = "Hello? world";

    public String getHellString() {
        return hellString;
    }

    public void setHellString(String hellString) {
        this.hellString = hellString;
    }

}
