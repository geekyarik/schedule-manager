package com.ystan.schedule.filters;

import com.ystan.schedule.security.JSONWebTokenUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JSONWebTokenRequestFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String JWT_TOKEN_START = "Bearer ";

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JSONWebTokenUtils jsonWebTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        final String header = httpServletRequest.getHeader(AUTH_HEADER);
        String username = null;
        String jwt = null;

        if (Strings.isNotEmpty(header) && header.startsWith(JWT_TOKEN_START)) {
            jwt = header.replace(JWT_TOKEN_START, Strings.EMPTY);
            username = jsonWebTokenUtils.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jsonWebTokenUtils.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
