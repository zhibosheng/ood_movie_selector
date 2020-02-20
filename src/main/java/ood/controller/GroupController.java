package ood.controller;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GroupController {
    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/group/{userId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Group getGroupById(@PathVariable(name = "groupId") long groupId){
        return groupService.getGroupById(groupId);
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
    public Group createGroup(@RequestBody User user){
        return groupService.createGroup(user);
    }

    @RequestMapping(value = "/group/history",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Event> getHistory(@RequestBody Group group){
        return groupService.getHistory(group);
    }

    @RequestMapping(value = "/group/inviteGroupEmail",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean sendInviteGroupEmail(@RequestBody Group group, String email){ return groupService.sendInviteGroupEmail(group, email);}
}
