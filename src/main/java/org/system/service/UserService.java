package org.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.exceptions.NotFoundUser;
import org.system.model.User;
import org.system.record.DataListUser;
import org.system.record.DataUpdateUser;
import org.system.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public DataListUser getUser(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new NotFoundUser("Nenhum usuário encontrado com o id: " + id));
        return new DataListUser(user.getId(), user.getName(), user.getPhoneNumber());
    }

    public DataListUser updateUser(DataUpdateUser data) {
        User user = userRepository.findById(data.id())
                .orElseThrow(() -> new NotFoundUser("Nenhum usuário encontrado com o id:" + data.id()));

       return user.updateData(data);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new NotFoundUser("Nenhum usuário encontrado com o id:" + id));

        userRepository.deleteById(user.getId());
    }

}
