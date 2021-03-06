package thirtynine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import thirtynine.pojo.User;
import thirtynine.service.UserService;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class Login {

    @Autowired
    private UserService userService;

    /**
     * go to login page
     */
    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    /**
     * if login successfully
     */
    @PostMapping("/login")
    public String LoginJudge(User user, HttpSession session, Map<String,Object> map){
        if(userService.login(user)){
            user = userService.getByName(user.getName());//give it user info
            session.setAttribute("user",user);//save user info in session
            return "redirect:ChatRoom";
        }else{
            map.put("fail","This username has been registered and your password is wrong");
            return "login";
        }
    }
}
