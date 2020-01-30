package ood.controller;


import ood.model.User;
import ood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public User update(@RequestBody User user){
        return userService.update(user);
    }

    @RequestMapping(value = "/user",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@RequestBody User user){
        return userService.delete(user);
    }
}
