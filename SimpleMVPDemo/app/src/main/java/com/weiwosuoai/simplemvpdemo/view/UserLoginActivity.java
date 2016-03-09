package com.weiwosuoai.simplemvpdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.weiwosuoai.simplemvpdemo.R;
import com.weiwosuoai.simplemvpdemo.bean.User;
import com.weiwosuoai.simplemvpdemo.presenter.UserLoginPresenter;

public class UserLoginActivity extends AppCompatActivity implements IUserLoginView {

    private EditText mEdtUserName, mEdtPassWord;
    private Button mBtnLogin, mBtnCancle;
    private ProgressBar mPbLoading;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mEdtPassWord = (EditText) findViewById(R.id.edt_password);
        mEdtUserName = (EditText) findViewById(R.id.edt_username);

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnCancle = (Button) findViewById(R.id.btn_cancle);

        mPbLoading = (ProgressBar) findViewById(R.id.pb);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
            }
        });

        mBtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.cancle();
            }
        });
    }

    @Override
    public String getUserName() {
        return mEdtUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEdtPassWord.getText().toString();
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUserName() + " login success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedMsg() {
        Toast.makeText(this, "login fail!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        this.finish();
    }
}
