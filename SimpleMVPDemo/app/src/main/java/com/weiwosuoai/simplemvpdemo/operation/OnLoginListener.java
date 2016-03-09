package com.weiwosuoai.simplemvpdemo.operation;

import com.weiwosuoai.simplemvpdemo.bean.User;

/**
 * Created by jiangbing on 2016/3/8.
 */
public interface OnLoginListener {

    void onLoginSuccess(User user);
    void onLoginFailed();
}
