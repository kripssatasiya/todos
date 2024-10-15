package com.enablero.todo.security;

import com.enablero.todo.entity.User;
import com.enablero.todo.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@WebFilter
@Component
public class SecurityFilter implements Filter {

    private final UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       try{
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

           if(authentication != null){
               Jwt jwt = (Jwt) authentication.getPrincipal();

               String email = jwt.getClaim("email");

               User user = userRepository.findByEmail(email);

               if(user == null){
                   SecurityContextHolder.getContext().setAuthentication(null);
                   response.setContentType("application/json");
                   response.getWriter().write("{\"message\": \"Unsuccessful\", \"details\": \"email id: " + email + " is not found!\", \"httpStatusCode\": \"404 (USER_NOT_FOUND)\"}");
                   response.getWriter().flush();
               }
               else{
                   JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                   jwtAuthenticationToken.setDetails(user);
               }
           }
           chain.doFilter(request,response);
       }
       catch (Exception e){
          SecurityContextHolder.clearContext();
           response.setContentType("application/json");
           response.setCharacterEncoding("UTF-8");
           response.getWriter().write("{\"message\": \"Unsuccessful\", \"details\": \"An error occurred during authentication: " + e.getMessage() + "\", \"httpStatusCode\": \"401 (UNAUTHORIZED)\"}");
           response.getWriter().flush();
       }
    }
}
