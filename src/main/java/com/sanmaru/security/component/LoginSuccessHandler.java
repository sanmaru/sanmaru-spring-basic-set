package com.sanmaru.security.component;

import com.sanmaru.entities.UserHistory;
import com.sanmaru.repositories.UserHistoryRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Date;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    final static Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    UserHistoryRepository userHistoryRepository;
    private String  tokenName;

    public LoginSuccessHandler(UserHistoryRepository userHistoryRepository) {
        super();
        this.userHistoryRepository = userHistoryRepository;
        this.tokenName = "User_Token";
    }
    public LoginSuccessHandler(UserHistoryRepository userHistoryRepository, String tokenName) {
        super();
        this.userHistoryRepository = userHistoryRepository;
        this.tokenName = tokenName;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        UserDetails userDetails
                = (UserDetails) authentication.getPrincipal();


        logger.info("========= LoginSuccessHandler "  + request.getLocalAddr());
        logger.info("========= LoginSuccessHandler "  + request.getRemoteHost());
        logger.info("========= LoginSuccessHandler "  + request.getLocalName());
        logger.info("========= LoginSuccessHandler "  + request.getRequestURI());
        logger.info("========= LoginSuccessHandler "  + request.getContextPath());
        logger.info("========= LoginSuccessHandler "  + request.getPathInfo());
        logger.info("========= LoginSuccessHandler "  + request.getHeader("user-agent"));


        userHistoryRepository.save(new UserHistory(request, userDetails.getUsername(), "사용자 로그인"));
        // We need a signing key, so we'll create one just for this example. Usually
        // the key would be read from your application configuration instead.
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
        String jws = Jwts.builder()
                .claim("username", userDetails.getUsername() )
                .setIssuedAt(new Date())
                .setExpiration(new Date())
                .signWith(key)
                .compact();

        logger.info("+++++++++ tokenName : " + tokenName);
        Cookie cookie = new Cookie(tokenName, jws);
        response.addCookie(cookie);

        logger.info("++++++++++++++++ SignatureAlgorithm.HS256 " + SignatureAlgorithm.HS256 );
        logger.info("+++++++++++++++ LoginSuccessHandler " + jws);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
