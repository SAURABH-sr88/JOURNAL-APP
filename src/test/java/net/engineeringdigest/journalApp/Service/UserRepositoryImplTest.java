package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Disabled
    @Test
    public void getUserForSATest(){
        Assertions.assertNotNull(userRepository.getUserForSA());
    }

}
