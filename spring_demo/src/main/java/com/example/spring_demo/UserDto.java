package com.example.spring_demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private String name;
    private int age;
}

/*public class UserDto {

    private String name;
    private int age;

    // Getter 메서드
    public String getName() {
        return name;
    }

    // Setter 메서드
    public void setName(String name) {
        this.name = name;
    }

    // Getter 메서드
    public int getAge() {
        return age;
    }

    // Setter 메서드
    public void setAge(int age) {
        this.age = age;
    }

    // ToString 메서드
    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}*/

