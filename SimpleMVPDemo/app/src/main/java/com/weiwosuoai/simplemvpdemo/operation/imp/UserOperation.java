package com.weiwosuoai.simplemvpdemo.operation.imp;

import com.weiwosuoai.simplemvpdemo.bean.User;
import com.weiwosuoai.simplemvpdemo.operation.IUserOperation;
import com.weiwosuoai.simplemvpdemo.operation.OnLoginListener;

/**
 * Created by jiangbing on 2016/3/8.
 */
public class UserOperation implements IUserOperation {


    @Override
    public void login(final String userName, final String passWord, final OnLoginListener loginListener) {
        // 开启一个线程用来模拟用户登陆
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    // 验证登陆
                    if ("allen".equals(userName) && "123456".equals(passWord)) {
                        User user = new User();
                        user.setUserName(userName);
                        user.setPassWord(passWord);
                        loginListener.onLoginSuccess(user);
                    } else {
                        loginListener.onLoginFailed();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
