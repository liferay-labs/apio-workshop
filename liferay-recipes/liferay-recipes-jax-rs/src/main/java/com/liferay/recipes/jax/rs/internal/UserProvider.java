package com.liferay.recipes.jax.rs.internal;

import com.liferay.apio.architect.provider.Provider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserService;
import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;

@Component(
    immediate = true, property = "osgi.jaxrs.extension=true",
    service = {ContextProvider.class, Provider.class}
)
public class UserProvider implements Provider<User>, ContextProvider<User> {

    @Override
    public User createContext(Message message) {
        try {
            return _userService.getCurrentUser();
        } catch (PortalException e) {
            throw new NotAuthorizedException(e, "Basic");
        }
    }

    @Override
    public User createContext(HttpServletRequest httpServletRequest) {
        try {
            return _userService.getCurrentUser();
        } catch (PortalException e) {
            throw new NotAuthorizedException(e, "Basic");
        }
    }

    @Reference
    private UserService _userService;

}
