package com.enablero.todo.security;

import com.enablero.todo.entity.UserEntity;
import com.enablero.todo.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@WebFilter
@Component
public class SecurityFilter implements Filter {

    private final UserRepository userRepository;

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//       try{
//           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//           if(authentication != null){
//               Jwt jwt = (Jwt) authentication.getPrincipal();
//
//               String email = jwt.getClaim("email");
//               System.out.println("email " + email);
//
//               UserEntity user = userRepository.findByEmail(email);
//               System.out.println("user " + user);
//
//               if(user == null){
//                   SecurityContextHolder.getContext().setAuthentication(null);
//                   response.setContentType("application/json");
//                   response.getWriter().write("{\"message\": \"Unsuccessful\", \"details\": \"email id: " + email + " is not found!\", \"httpStatusCode\": \"404 (USER_NOT_FOUND)\"}");
//                   response.getWriter().flush();
//               }
//               else{
//                   JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//                   jwtAuthenticationToken.setDetails(user);
//               }
//           }
//           chain.doFilter(request,response);
//       }
//       catch (Exception e){
//          SecurityContextHolder.clearContext();
//           response.setContentType("application/json");
//           response.setCharacterEncoding("UTF-8");
//           response.getWriter().write("{\"message\": \"Unsuccessful\", \"details\": \"An error occurred during authentication: " + e.getMessage() + "\", \"httpStatusCode\": \"401 (UNAUTHORIZED)\"}");
//           response.getWriter().flush();
//       }
//    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null) {

                String authHeader = ((HttpServletRequest) request).getHeader("Authorization");
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);


                    Jwt jwt = Jwt.withTokenValue(token)
                            .header("alg", "RS256")
                            .claims(jwtClaims -> {
                                jwtClaims.put("email", "user@example.com");
                            })
                            .build();


                    String email = jwt.getClaimAsString("email");
                    System.out.println("email "+email);

                    UserEntity user = userRepository.findByEmail(email);
                    System.out.println("user "+user);
                    if (user != null) {
                        authentication = new JwtAuthenticationToken(jwt, List.of());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        SecurityContextHolder.clearContext();
                        response.setContentType("application/json");
                        response.getWriter().write("{\"message\": \"Unsuccessful\", \"details\": \"User not found for email: " + email + "\", \"httpStatusCode\": \"404 (USER_NOT_FOUND)\"}");
                        response.getWriter().flush();
                        return;
                    }
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"Unsuccessful\", \"details\": \"An error occurred during authentication: " + e.getMessage() + "\", \"httpStatusCode\": \"401 (UNAUTHORIZED)\"}");
            response.getWriter().flush();
        }
    }
}
