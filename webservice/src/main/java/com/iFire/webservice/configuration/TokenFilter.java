package com.ifire.webservice.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.ifire.webservice.auth.token.TokenService;
import com.ifire.webservice.user.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            User user = tokenService.verifyToken(authorizationHeader);
            if (user != null) {
                System.err.println(user.isActive());
                if (!user.isActive()) {
                    // throw new DisabledException("User is disabled");

                    exceptionResolver.resolveException(request, response, null, new DisabledException("User is disabled"));

                    // ApiError apiError = new ApiError();
                    // apiError.setStatus(401);
                    // apiError.setMessage("User is disable");
                    // apiError.setPath(request.getRequestURI());
                    // ObjectMapper oMapper = new ObjectMapper();

                    // response.setStatus(401);
                    // response.setContentType("apilication/json");
                    // OutputStream os = response.getOutputStream();
                    // oMapper.writeValue(os, apiError);
                    // os.flush();
                    return;
                }
                CurrentUser currentUser = new CurrentUser(user);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        currentUser, null, currentUser.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        System.err.println("-----------");
        System.err.println("filter is running");
        System.err.println("-----------");
        filterChain.doFilter(request, response);
    }

}
