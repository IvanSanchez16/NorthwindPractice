package com.coppel.filters;


import com.coppel.config.AppConfig;
import com.coppel.dto.ApiResponseDTO;
import com.coppel.util.AppMessages;
import com.coppel.util.Meta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.coppel.util.Vulnerabilidades.sanitiziedString;


/**
* SessionFilter
*/
@Component
@Order(value = 1)
public class SessionFilter implements Filter {
    
    @Autowired
    private AppConfig config;
    
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) 
            throws IOException, ServletException {
        if (config.isIgnoreSession()) {
            chain.doFilter(request, response);
            return;
        }
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        if (req.getHeader(HttpHeaders.AUTHORIZATION) == null) {
            generaResponseUnAuth(res);
        } else {
            final RestTemplate client = new RestTemplate();
            final HttpHeaders authHeaders = new HttpHeaders();
            authHeaders.add(HttpHeaders.AUTHORIZATION, sanitiziedString(req.getHeader(HttpHeaders.AUTHORIZATION)));
            final HttpEntity<String> httpEntity = new HttpEntity<>(authHeaders);
            try {
                final ResponseEntity<String> authResponse = client.exchange(config.getAuthUri(), HttpMethod.POST, httpEntity, String.class);
                if (authResponse.getStatusCode() == HttpStatus.OK) {
                    chain.doFilter(request, response);
                }
            } catch (RestClientException ex) {
                generaResponseUnAuth(res);
            }
        }
    }

    private void generaResponseUnAuth(HttpServletResponse res) throws IOException {
        final ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        final ObjectMapper objectMapper = new ObjectMapper();

        res.reset();
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, config.getAllowedOrigins());
        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, config.getAllowedMethods());
        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, config.getAllowedHeaders());
        res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        res.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");

        res.setCharacterEncoding("UTF-8");
        apiResponseDTO.setMeta(new Meta(null, AppMessages.CLIENT_ERROR, 401, AppMessages.UNAUTHORISED_MESSAGE));
        res.getWriter().write(objectMapper.writeValueAsString(apiResponseDTO));
    }
}
