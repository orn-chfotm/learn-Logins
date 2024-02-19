package com.learnlogins.back.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnlogins.back.data.dto.NaverDTO;
import com.learnlogins.back.service.NaverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Log4j2
public class NaverServiceImpl implements NaverService {

    @Value("${naver.client.id}")
    private String NAVER_CLIENT_ID;

    @Value("${naver.client.secret}")
    private String NAVER_CLIENT_SECRET;

    @Value("${naver.redirect.url}")
    private String NAVER_REDIRECT_URL;

    private final String NAVER_AUTH_URI = "https://nid.naver.com";
    private final String NAVER_API_URI = "https://openapi.naver.com";

    private final RestTemplate restTemplate;

    @Override
    public String getLoginUrl(){
        StringBuffer naverLogin = new StringBuffer(NAVER_AUTH_URI);

        naverLogin.append("/oauth2.0/authorize" +
                        "")
                .append("?client_id=").append(NAVER_CLIENT_ID)
                .append("&redirect_uri=").append(NAVER_REDIRECT_URL)
                .append("&state=").append("test")
                .append("&response_type=code");

        return naverLogin.toString();
    }

    @Override
    public NaverDTO getNaverInfo(String code, String state) throws Exception {
        if(code == null || state == null) throw new Exception("Failed get authorization code");

        String accessToken = "";
        String refreshToken = "";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", NAVER_CLIENT_ID);
            params.add("client_secret", NAVER_CLIENT_SECRET);
            params.add("code", code);
            params.add("state", state);

            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    NAVER_AUTH_URI + "/oauth2.0/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());

            accessToken = (String) jsonObject.get("access_token");
            refreshToken = (String) jsonObject.get("refresh_token");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception Message :: " + e.getMessage());
            throw new Exception("API Call failed Exception");
        }
        return getUserInfoWithToken(accessToken);
    }

    @Override
    public NaverDTO getUserInfoWithToken(String accessToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                NAVER_API_URI + "/v1/nid/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
        String resultCode = (String) jsonObject.get("resultcode");
        String message = (String) jsonObject.get("message");

        ObjectMapper objectMapper = new ObjectMapper();
        NaverDTO naverDTO = objectMapper.readValue(jsonObject.get("response").toString(), NaverDTO.class);

        return naverDTO;
    }

}
