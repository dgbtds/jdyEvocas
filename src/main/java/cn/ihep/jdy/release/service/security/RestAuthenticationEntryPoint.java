package cn.ihep.jdy.release.service.security;

import cn.ihep.jdy.release.dao.model.ResultModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResultModel result = new ResultModel(1001);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);

        response.setContentType("application/json; charset=utf-8");
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}