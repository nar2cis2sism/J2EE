package web.app;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;

import web.app.setting.AuthorizationStrategy;
import web.app.ui.page.login.Login;

public class WicketApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return Login.class;
    }
    
    @Override
    protected void init() {
        super.init();
        
        getSecuritySettings().setAuthorizationStrategy(new AuthorizationStrategy());
    }
    
    @Override
    public RuntimeConfigurationType getConfigurationType() {
        return RuntimeConfigurationType.DEPLOYMENT;
    }
}