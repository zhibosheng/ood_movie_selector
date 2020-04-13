package ood.controller;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.service.GroupService;
import ood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/group/{groupId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Group getGroupById(@PathVariable(name = "groupId") long groupId){
        return groupService.getGroupById(groupId);
    }

    @RequestMapping(value = "/group/event",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Group getGroupWithEvent(@RequestParam long groupId){
        return groupService.getGroupWithEvent(groupId);
    }

    @RequestMapping(value = "/group/user",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Group getGroupWithUser(@RequestParam long groupId){
        return groupService.getGroupWithUser(groupId);
    }


    @RequestMapping(value = "/group",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Group save(@RequestBody Group group){
        return groupService.save(group);
    }

    @RequestMapping(value = "/group",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Group update(@RequestBody Group group){
        return groupService.update(group);
    }

    @RequestMapping(value = "/group",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@RequestBody Group group){
        return groupService.delete(group);
    }

    @RequestMapping(value = "/group/creation",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Group createGroup(@RequestParam String userName, @RequestParam String groupName, @RequestParam String groupDescription){
//        System.out.println(userName);
//        System.out.println(groupName);
//        System.out.println(groupDescription);
        return groupService.createGroup(userService.getUserByName(userName),groupName,groupDescription);
    }

    @RequestMapping(value = "/group/history",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Event> getHistory(@RequestParam String groupName){
        Group group = groupService.getGroupByName(groupName);
        return groupService.getHistory(group);
    }

    @RequestMapping(value = "/group/inviteGroupEmail",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean sendInviteGroupEmail(@RequestParam String groupName, @RequestParam String email){
        return groupService.sendInviteGroupEmail(groupService.getGroupByName(groupName), email);
    }

    @RequestMapping(value = "/group/default",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap getDefaultMovies(){
        return groupService.getDefaultMovies();
    }

}
