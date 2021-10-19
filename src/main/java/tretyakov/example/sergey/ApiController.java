package tretyakov.example.sergey;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ApiController {
    private List<String> messages;

    {
        messages = new ArrayList<>();
        messages.add("serg");
        messages.add("serginia");
        messages.add("serginat");
        messages.add("la_serj");
    }

    // curl --get http://localhost:8080/messages
    @GetMapping("messages")
    public List<String> getMessages() {
        return messages;
    }

    //curl --get http://localhost:8080/messages/search/la_serj
    @GetMapping("messages/search/{text}")
    public int getIndexOf(@PathVariable("text") String text) {
        return messages.indexOf(text);
    }

    //curl --get http://localhost:8080/messages/count
    @GetMapping("messages/count")
    public int getSize(){
        return messages.size();
    }

    /* curl -X POST http://localhost:8080/messages -H 'Content-Type:
   text/plain' -d 'text' */
    @PostMapping("messages")
    public void addMessage(@RequestBody String text) {
        messages.add(text);
    }

    // curl --get http://localhost:8080/messages/1
    @GetMapping("messages/{index}")
    public String getMessage(@PathVariable("index") Integer index) {
        return messages.get(index);
    }

    // curl -X POST http://localhost:8080/messages/2/create
    @PostMapping("messages/{index}/create")
    public void addMessageAt(@PathVariable("index") Integer index) {
        messages.add(messages.get(index));
    }

    //curl -X DELETE http://localhost:8080/messages/search/serg
    @DeleteMapping("messages/search/{text}")
    public void deleteAll(@PathVariable("text") String text)
    {
        messages.removeIf(message -> message.contains(text));
    }

    // curl -X DELETE http://localhost:8080/messages/1
    @DeleteMapping("messages/{index}")
    public void deleteText(@PathVariable("index") Integer index) {
        messages.remove((int) index);
    }

    //curl --put http://localhost:8080/messages/0 -H 'Content-Type:
    //   text/plain' -d 'la_serj'
    @PutMapping("messages/{index}")
    public void updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
    }
}