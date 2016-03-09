package com.weiwosuoai.simplemvpdemo.operation;

/**
 * Created by jiangbing on 2016/3/8.
 */
public interface IUserOperation {

    void login(String userName, String passWord, OnLoginListener loginListener);
}
