package com.weiwosuoai.simplemvpdemo.view;

import com.weiwosuoai.simplemvpdemo.bean.User;

/**
 * Created by jiangbing on 2016/3/8.
 */
public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedMsg();

    void finish();
}
