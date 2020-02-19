package ood.controller;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public class GroupController {
    @Autowired
    GroupService groupService;

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
}
