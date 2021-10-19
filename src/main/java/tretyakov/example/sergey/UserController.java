package tretyakov.example.sergey;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class UserController {
    private List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User("Sergey", 16));
        users.add(new User("Serginat", 26));
        users.add(new User("Serg", 37));
    }


    // curl --get http://localhost:8080/users
    @GetMapping("users")
    public List<User> getUsers() {
        return users;
    }

    //curl -X POST http://localhost:8080/users/create -H "Content-Type: application/json" -d "{\"name\":\"serj\",\"age\":100}"
    @PostMapping("users/create")
    public void addUser(@Validated @RequestBody User user) {
        users.add(user);
    }

    //curl -X DELETE http://localhost:8080/users/delete/1
    @DeleteMapping("users/delete/{index}")
    public void removeUserAt(@PathVariable("index") Integer index) {
        users.remove((int) index);
    }

    //curl --get http://localhost:8080/users/1
    @GetMapping("users/{index}")
    public User getUserAt(@PathVariable("index") Integer index){
        return users.get(index);
    }

    //curl -X PUT http://localhost:8080/users/update/1 -H "Content-Type: application/json" -d "{\"age\": 69}"
    @PutMapping("users/update/{index}")
    public void updateAge(@PathVariable("index") Integer index, @Validated @RequestBody User user)
    {
        users.get(index).setAge(user.getAge());
    }

}