package ood.controller;

import ood.model.User;
import ood.service.UserService;
import ood.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping(value = ("/auth"))
public class Authentication {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String errorMsg = "The name or password is wrong.";
    private String tokenKeyWord = "Authorization";
    private String tokenType = "Bearer";



    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity authenticate(@RequestBody User user){

        String token = "";

        try {
            logger.info(user.toString());
            logger.info(user.getUserName()+"   "+ user.getPassword());
            User u = userService.getUserByCredentials(user.getUserName(), user.getPassword());

            logger.info(u.toString());

            if(u == null) u = userService.getUserByCredentials(user.getUserId(), user.getPassword());
            if (u == null) return ResponseEntity.status(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION).body(errorMsg);
            logger.info(u.toString());
            token = JwtUtil.generateToken(u);
            logger.info(token);
        }
        catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            if (msg == null) msg = "BAD REQUEST!";
            logger.error(msg);
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(msg);
        }
//        return ResponseEntity.status(HttpServletResponse.SC_OK).body(tokenKeyWord + ":" + tokenType + " " + token);
        HashMap<String, String> tokenJson = new HashMap<>();
        tokenJson.put(tokenKeyWord,token);
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(tokenJson);
        //return ResponseEntity.status(HttpServletResponse.SC_OK).body("Login successfully!!!");
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST, produces = "application/json")
    public User save(@RequestBody User user){
        return userService.save(user);
    }
}
