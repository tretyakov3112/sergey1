package tretyakov.example.sergey;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {


    private String name;
    private Integer age;

    User(String name, Integer age)
    {
        this.age = age;
        this.name = name;
    }

    User(Integer age)
    {
        this("Serj", age);
    }

}
