package com.today.rocactiviti;

import com.today.rocactiviti.web.LeaveController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocActivitiApplication.class)
public class RocActivitiApplicationTests {

    @Autowired
    public LeaveController leaveController;

    @Test
    public void test() throws FileNotFoundException {
        leaveController.deploy2();
    }

}
