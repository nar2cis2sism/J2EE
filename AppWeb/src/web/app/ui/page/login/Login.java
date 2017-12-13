package web.app.ui.page.login;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import web.app.WicketSession;
import web.app.storage.dao.UserDAO;
import web.app.storage.db.User;
import web.app.ui.page.main.Main;
import web.app.ui.widget.tip.Tip;
import wicket.framework.BasePage;
import wicket.framework.annotation.ComponentInjector;
import wicket.framework.annotation.FormInjector;
import wicket.framework.annotation.ModelInjector;

@SuppressWarnings("serial")
public class Login extends BasePage {
    
    @ModelInjector
    LoginModel model;
    
    @ComponentInjector
    Tip tips;
    @ComponentInjector @FormInjector(onSubmit="login")
    Form<?> form;
    @ComponentInjector
    TextField<String> username;
    
    void login() {
        String username = model.username;
        User user = UserDAO.getUserByUsername(username);
        if (user != null)
        {
            // 登录成功
            WicketSession.saveUser(user);
            continueToOriginalDestination();
            setResponsePage(Main.class);
        }
        else
        {
            tips.showTip(String.format("用户%s不存在", username));
            model.username = null;
        }
    }
}