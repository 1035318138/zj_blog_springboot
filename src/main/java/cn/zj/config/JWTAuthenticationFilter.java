package cn.zj.config;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.channels.Channel;
import java.util.ArrayList;

/**
 * Created by Sept.05 on 2019/8/8.
 *
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 */
public class JWTAuthenticationFilter /*extends BasicAuthenticationFilter*/{
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
//		super(authenticationManager);
	}

//	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//		super.doFilterInternal(request, response, chain);
		String header = request.getHeader("Authorization");

		if(header == null || !header.startsWith("Bearer ")){
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getauthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

	}

	private UsernamePasswordAuthenticationToken getauthentication(HttpServletRequest req){
		String token = req.getHeader("Authorization");
		if(token != null){
			/// parse the token.
			String user = Jwts.parser()
							  .setSigningKey("MyJwtSecret")
							  .parseClaimsJws(token.replace("Bearer ", ""))
							  .getBody()
							  .getSubject();
			if(user != null){
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}
}
