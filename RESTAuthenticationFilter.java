package com.blogger.rest.filters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.blogger.rest.store.ErrorStore;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class RESTAuthenticationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		Method resourceMethod = resourceInfo.getResourceMethod();

		if (resourceMethod.isAnnotationPresent(DenyAll.class)) {
			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
					.entity(ErrorStore.getErrorResponse(3000, "API Blocked for all Users")).build());
		}

		if (!resourceMethod.isAnnotationPresent(PermitAll.class)) {
			MultivaluedMap<String, String> headers = requestContext.getHeaders();

			if (headers == null) {
				requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
						.entity(ErrorStore.getErrorResponse(3001, "Auth Token Missing in header")).build());
			}

			String authPass = headers.getFirst("Authorization");
			if (authPass == null || authPass.trim().isEmpty() || !authPass.startsWith("Basic ")) {
				requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
						.entity(ErrorStore.getErrorResponse(3001, "Auth Token Missing in header")).build());
			} else {

			String userPass[] = authPass.split(" ");

			try {
				StringTokenizer tokenizer = new StringTokenizer(
						new String(com.sun.org.apache.xml.internal.security.utils.Base64.decode(userPass[1])), ":");
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();

				if (!username.equals("admin") || !password.equals("admin")) {
					requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
							.entity(ErrorStore.getErrorResponse(3003, "Invalid Auth Token")).build());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}

		}

	}

}
