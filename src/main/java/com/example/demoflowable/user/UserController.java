package com.example.demoflowable.user;

import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.idm.api.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private ProcessEngine processEngine;

    @GetMapping
    public ResponseEntity list() {
        IdentityService identityService = processEngine.getIdentityService();
        List<User> list = identityService.createUserQuery().list();
        return ok(list);
    }
}
