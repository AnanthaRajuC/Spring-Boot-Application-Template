package io.github.anantharajuc.sbat.core_backend.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
    private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException 
	{
		String jwt = getJwtFromRequest(httpServletRequest);
		
		if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt))
		{
			String username = jwtProvider.getUsernameFromJwt(jwt);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
	
	private String getJwtFromRequest(HttpServletRequest request)
	{
		String bearerToken = request.getHeader("Authorization");
		
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) 
		{
            return bearerToken.substring(7);
        }
		
		return bearerToken;
	}
}
