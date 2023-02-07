package com.restapi.adminBackend.jwt;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.restapi.adminBackend.config.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;	
	@Autowired
	private JwtUtils jwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//fetch authorisation header
		
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println("Requested Token is :"+requestTokenHeader);
		final String userName ;
		final String jwtToken ;
		if (requestTokenHeader == null ||!requestTokenHeader.startsWith("Bearer ")) {
		      filterChain.doFilter(request, response);
		      return;
		    }
		jwtToken = requestTokenHeader.substring(7);
		userName = this.jwtUtil.extractUsername(jwtToken);
//		if (requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")) {			
//			jwtToken = requestTokenHeader.substring(7);
//			try {
//				userName = this.jwtUtil.extractUsername(jwtToken);
//			} catch (ExpiredJwtException e) {
//				System.out.println("JWT Token Expired");
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("error");
//			}
//		}else {
//			System.out.println("Invalid Token, not start with bearer string");
//		}
			
		//once we get the token ...validate token
		
		if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(userName);
			if(this.jwtUtil.isTokenValid(jwtToken, userDetails)) {
				//token is valid
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
				authToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
						);
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}		
		}		
		filterChain.doFilter(request, response);		
	}
}
