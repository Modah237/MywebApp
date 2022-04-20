package com.mycompany.mywebapp;

import com.mycompany.mywebapp.user.User;
import com.mycompany.mywebapp.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired private UserRepository repo;

    @Test
    public void testAddNewUser(){
        User user = new User();
        user.setEmail("quica@gmail.com");
        user.setFirstName("quica");
        user.setLastName("le ");
        user.setPassword("123");

        User SavedUser = repo.save(user);

        Assertions.assertThat(SavedUser);
        Assertions.assertThat(SavedUser.getId()).isGreaterThan(0);

    }
    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User  user: users){
            System.out.println(users);
        }
    }

    @Test
    public void testUpdate(){

        Integer user_id=1;
        Optional<User> optionalUser = repo.findById(user_id);
        User user = optionalUser.get();
        user.setPassword("HELLOOOOO");
        repo.save(user);

        Optional<User> UpdatedUser = repo.findById(user_id);
        Assertions.assertThat(UpdatedUser.get().getPassword()).isEqualTo("HELLOOOOO");
    }

    @Test
    public void testDelete(){
        Integer userId= 2;

        repo.deleteById(userId);

        Optional<User> userOptional = repo.findById(userId);
        Assertions.assertThat(userOptional).isNotPresent();




    }

}
