package ood.controller;


import ood.model.Group;
import ood.model.User;
import ood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public User getUserById(@PathVariable(name = "userId") long userId){
        return userService.getUserById(userId);
    }

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

    //requestmapping
    //public List<Group> getOwnGroups(User user){ return userService.getOwnGroups(user);}

    //requestmapping
    //public List<Group> getJoinGroups(User user){ return userService.getJoinGroups(user);}

    @RequestMapping(value = "/user/ownGroup",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public User addOwnGroup(@RequestBody User user){ return userService.addOwnGroup(user);}

    @RequestMapping(value = "/user/manageGroup",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public User addOwnGroup(@RequestBody User user, Group group){ return userService.addOwnGroup(user,group);}

    @RequestMapping(value = "/user/ownGroup",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public User removeOwnGroup(@RequestBody User user, Group group){ return userService.removeOwnGroup(user,group);}

    @RequestMapping(value = "/user/joinGroup",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public User joinGroup(@RequestBody User user, Group group){ return userService.joinGroup(user,group);}

    @RequestMapping(value = "/user/joinGroup",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public User leaveGroup(@RequestBody User user, Group group){ return userService.leaveGroup(user,group);}


}
