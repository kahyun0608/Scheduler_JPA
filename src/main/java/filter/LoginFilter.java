package filter;

import common.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/users/signup", "/login"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        //로그인을 체크해야하는 url인지 검사
        if (!isWhiteList(requestURI)) {
            //WHITE_LIST에 없는 url 이므로 세션이 있는지 가져온다 -> getSession(false)라서 없을시 새로 생성 X
            HttpSession session = httpRequest.getSession(false);

            //로그인을 하지 않아 세션이 없는 경우
            if(session == null || session.getAttribute(Const.LOGIN_USER) == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 해주세요.");
            }
            //로그인 성공 로직
            log.info("로그인에 성공했습니다.");
        }

        //Servlet -> Controller 호출
        chain.doFilter(request, response);

    }

    //WHITE_LIST에 포함된 url인지 검사
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

}
