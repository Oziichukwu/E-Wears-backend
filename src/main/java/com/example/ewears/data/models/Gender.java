package com.example.ewears.data.models;

public enum Gender {
    MALE,
    FEMALE;

    @Override
    public String toString(){

        String value = "";
        switch (this){
            case MALE -> value = "Male";

            case FEMALE -> value = "Female";
        }
        return value;

    }
}
