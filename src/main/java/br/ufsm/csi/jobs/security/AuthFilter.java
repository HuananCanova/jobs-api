package br.ufsm.csi.jobs.security;

import br.ufsm.csi.jobs.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private final TokenServiceJWT tokenServiceJWT;
    private final AuthService authService;

    public AuthFilter(TokenServiceJWT tokenServiceJWT, AuthService authService) {
        this.tokenServiceJWT = tokenServiceJWT;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filter auth");


        String tokenJWT = reclaimToken(request);
        System.out.println("JWT TOKEN: " + tokenJWT);
        if (tokenJWT != null){
            String subject = tokenServiceJWT.getSubject(tokenJWT);
            System.out.println("SUBJECT:" + subject);
            UserDetails userDetails = authService.loadUserByUsername(subject);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());


            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);
    }

    private String reclaimToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null){
            return token.replace("Bearer", "").trim();
        }
        return null;
    }

}
