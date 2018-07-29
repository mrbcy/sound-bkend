package cn.mrbcy.sound.controller;

import cn.mrbcy.sound.domain.Result;
import cn.mrbcy.sound.domain.User;
import cn.mrbcy.sound.service.UserService;
import cn.mrbcy.sound.util.JWTUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yang on 2018/7/14.
 */
@RestController
@RequestMapping(value = "/api/v1/")
public class AuthController extends AbstractRestHandler {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestParam("username")String userName,
                        @RequestParam("password")String password){
        User user = userService.findUserByName(userName);
        if(user == null){
            return new Result(401, "Username or password error.");
        }
        if(user.getPassword().equals(password)){
            String token = JWTUtil.sign(userName, password);
            userService.saveToken(userName,token);
            return new Result(200, "Login success.", token);
        }
        return new Result(401, "Username or password error.");
    }

    @DeleteMapping("users/{username}/tokens")
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    public Result invalidTokens(@PathVariable(value="username") String userName){
        userService.invalidToken(userName);
        return new Result(200, userName + "'s tokens have been invalid.");
    }

    @GetMapping("/401")
    public Result get401(){
        return new Result(401, "Authorization information expired, please log in again");
    }
}
