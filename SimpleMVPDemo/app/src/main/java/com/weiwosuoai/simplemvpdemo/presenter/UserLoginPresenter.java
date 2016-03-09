package com.weiwosuoai.simplemvpdemo.presenter;

import android.os.Handler;

import com.weiwosuoai.simplemvpdemo.bean.User;
import com.weiwosuoai.simplemvpdemo.operation.IUserOperation;
import com.weiwosuoai.simplemvpdemo.operation.OnLoginListener;
import com.weiwosuoai.simplemvpdemo.operation.imp.UserOperation;
import com.weiwosuoai.simplemvpdemo.view.IUserLoginView;

/**
 * Created by jiangbing on 2016/3/9.
 */
public class UserLoginPresenter {

    private IUserOperation userOperation;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userOperation = new UserOperation();
    }

    public void login() {
        userLoginView.showLoading();

        String userName = userLoginView.getUserName();
        String passWord = userLoginView.getPassword();
        userOperation.login(userName, passWord, new OnLoginListener() {
            @Override
            public void onLoginSuccess(final User user) {
                // 在UI线程中执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.hideLoading();
                        userLoginView.toMainActivity(user);
                    }
                });
            }

            @Override
            public void onLoginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.hideLoading();
                        userLoginView.showFailedMsg();
                    }
                });
            }
        });
    }

    public void cancle() {
        userLoginView.finish();
    }
}
