package v1.dewu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v1.dewu.entity.CodeUser;
import v1.dewu.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public boolean checkExistance(String id){
        return userRepository.existsById(id);
    }

    public CodeUser getUserById(String id)
    {
        return userRepository.findById(id).get();
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    public void saveOrUpdate(CodeUser user)
    {
        userRepository.save(user);
    }


}
