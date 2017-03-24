package com.example.sweet_xue.netrequest.util;

/**
 * Created by sweet_xue on 22/3/17.
 */

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}
