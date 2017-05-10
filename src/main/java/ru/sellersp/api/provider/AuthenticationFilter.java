package ru.sellersp.api.provider;

import ru.sellersp.api.beans.User;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Sanle on 08.05.2017.
 */
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();

        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Access blocked for all users !!")
                        .build());
                return;
            }

            //get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();

            // fetch authorization headers
            final List<String> authorization = headers.get(HttpHeaders.AUTHORIZATION);

            if (authorization == null || authorization.isEmpty()) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("You cannot access this resource")
                        .header(HttpHeaders.WWW_AUTHENTICATE,"Basic")
                        .build());
                return;
            }

            //get encoded username and password
            final String encodedUserNameAndPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
            //decode user name and password

            String userNameAndPassword = new String(Base64.getDecoder().decode(encodedUserNameAndPassword.getBytes()));
            final StringTokenizer tokenizer = new StringTokenizer(userNameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            //  verify user access
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

                //if user valid?
                if (!isUserAllowed(new User())) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You cannot access this resource")
                            .header(HttpHeaders.WWW_AUTHENTICATE,"Basic")
                            .build());
                    return;
                }
            }
        }
    }

    private boolean isUserAllowed(User user) {
        boolean isAllowed = false;

        return isAllowed;
    }
}
