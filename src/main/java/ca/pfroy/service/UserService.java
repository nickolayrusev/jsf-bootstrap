package ca.pfroy.service;

import ca.pfroy.domain.User;
import ca.pfroy.repository.RepositoryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by nikolayrusev on 2/7/16.
 */
@Stateless
public class UserService {
    @Inject
    RepositoryService repositoryService;

    public List<User> findAll(){
        return repositoryService.findAll(User.class);
    }
}
