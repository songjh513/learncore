package com.songjh.learncore.designpattern.adapter;

/**
 * Created  by songjh on 2019-06-13 21:02.
 */
public class LoginServiceAdapter {

    private ASystemLoginService aSystemLoginService;

    private BSystemLoginService bSystemLoginService;

    public void loginByQQ(String username,String password){
        aSystemLoginService.loginByQQ(username,password);
    }

    public void loginByWeichat(String username,String password){
        bSystemLoginService.loginByWeichat(username,password);
    }

    public ASystemLoginService getaSystemLoginService() {
        return aSystemLoginService;
    }

    public void setaSystemLoginService(ASystemLoginService aSystemLoginService) {
        this.aSystemLoginService = aSystemLoginService;
    }

    public BSystemLoginService getbSystemLoginService() {
        return bSystemLoginService;
    }

    public void setbSystemLoginService(BSystemLoginService bSystemLoginService) {
        this.bSystemLoginService = bSystemLoginService;
    }
}
