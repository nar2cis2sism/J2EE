package web.app.setting;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.IAuthorizationStrategy.AllowAllAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

import web.app.WicketSession;
import web.app.ui.page.login.Login;

public class AuthorizationStrategy extends AllowAllAuthorizationStrategy {
    
    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> c) {
        if (AuthorizedPage.class.isAssignableFrom(c))
        {
            if (WicketSession.getUser() == null)
            {
                // 不允许访问
                throw new RestartResponseAtInterceptPageException(Login.class);
            }
        }
        
        return true;
    }

    public interface AuthorizedPage {}
}