package ca.pfroy.controllers;

import ca.pfroy.domain.User;
import ca.pfroy.service.UserService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by nikolayrusev on 2/7/16.
 */
@ManagedBean
@Named("twitterAuthController")
public class TwitterAuthController {
    @Inject
    private UserService userService;

    private List<User> users;

    @PostConstruct
    public void init(){
        List<User> all = userService.findAll();
        this.users = all;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
