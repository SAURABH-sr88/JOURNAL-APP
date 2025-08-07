//package net.engineeringdigest.journalApp.Service;
//
//import net.engineeringdigest.journalApp.repository.UserRepository;
//import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
//import org.assertj.core.internal.Arrays;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.sql.Array;
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class UserDetailsServiceImplTest {
//    // this is when you want mock one or two injection and other initial with spring application context..
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    // this is when you don't want to initialization spring application context...
//
////    @InjectMocks
////    private UserDetailsServiceImpl userDetailsService;
////
////    @Mock
////    private UserRepository userRepository;
////
////    @BeforeEach
////    void setup(){
////        MockitoAnnotations.initMocks(this);
////    }
//    @Disabled
//    @Test
//    void loadUserByUsernameTest(){
//        when(userRepository.findByuserName(ArgumentMatchers.anyString())).thenReturn((net.engineeringdigest.journalApp.entity.User) User.builder().username("ram").password("dgchiudisuv").roles(String.valueOf(new ArrayList<>())).build());
//        UserDetails user = userDetailsService.loadUserByUsername("ram");
//
//    }
//
//
//}
