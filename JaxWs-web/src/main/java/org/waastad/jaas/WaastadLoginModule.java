/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.jaas;

import com.sun.security.auth.module.JndiLoginModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
public class WaastadLoginModule extends JndiLoginModule implements LoginModule {

    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private String login;
    private List<String> userGroups;

    @Override
    public void initialize(Subject sbjct, CallbackHandler ch, Map<String, ?> map, Map<String, ?> map1) {
        handler = ch;
        this.subject = sbjct;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
        try {
            handler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1])
                    .getPassword());
            if (name != null
                    && name.equals("user123")
                    && password != null
                    && password.equals("pass123")) {

                // We store the username and roles
                // fetched from the credentials provider
                // to be used later in commit() method.
                // For this tutorial we hard coded the
                // "admin" role
                login = name;
                userGroups = new ArrayList<>();
                userGroups.add("SuperAdminGroup");
                return true;
            }
            throw new LoginException("Authentication failed");
        } catch (IOException | UnsupportedCallbackException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        userPrincipal = new UserPrincipal(login);
        subject.getPrincipals().add(userPrincipal);

        if (userGroups != null && userGroups.size() > 0) {
            for (String groupName : userGroups) {
                rolePrincipal = new RolePrincipal(groupName);
                subject.getPrincipals().add(rolePrincipal);
            }
        }

        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(rolePrincipal);
        return true;
    }
}
