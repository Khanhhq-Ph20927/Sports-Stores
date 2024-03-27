package com.project.SportsStores.Toner.Filter;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


public class AdminInterceptor implements HandlerInterceptor {

    private static final String secret_key = "123";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = String.valueOf(request.getSession().getAttribute("token"));
        if (request.getRequestURI().startsWith("/admin/")) {
            System.out.println(token);
            if (token.equalsIgnoreCase("null")) {
                String loginPageUrl = "/auth-signin-basic";
                String redirectUrl = UriComponentsBuilder.fromUriString(request.getContextPath() + loginPageUrl)
                        .build().toUriString();
                response.sendRedirect(redirectUrl);
                return false;
            } else {
                String handleToken = token.replace("\"", "");
                Algorithm algorithm = Algorithm.HMAC256(secret_key.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(handleToken);
                String username = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                System.out.println("After Decode Token : " + username);
                for (String role : roles
                ) {
                    System.out.println("After Decode Token : " + role);
                    if (!role.equalsIgnoreCase("ADMIN")) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // Xử lý sau khi request đã được xử lý bởi controller nhưng trước khi trả về view
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // Xử lý sau khi response đã được gửi đi
    }
}
