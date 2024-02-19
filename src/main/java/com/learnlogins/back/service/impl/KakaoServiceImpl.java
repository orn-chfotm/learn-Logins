package com.learnlogins.back.service.impl;

import com.learnlogins.back.data.dto.KakaoDTO;
import com.learnlogins.back.service.KakaoService;
import lombok.RequiredArgsConstructor;
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
public class KakaoServiceImpl implements KakaoService {

    @Value("${kakao.client.id}")
    private String KAKAO_CLIENT_ID;

    @Value("${kakao.client.secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${kakao.redirect.url}")
    private String KAKAO_REDIRECT_URL;

    private final String KAKAO_AUTH_URI = "https://kauth.kakao.com";
    private final String KAKAO_API_URI = "https://kapi.kakao.com";

    public String getLoginUrl() {
        StringBuffer kakaoLogin = new StringBuffer(KAKAO_AUTH_URI);
        kakaoLogin.append("/oauth/authorize")
                .append("?client_id=").append(KAKAO_CLIENT_ID)
                .append("&redirect_uri=").append(KAKAO_REDIRECT_URL)
                .append("&response_type=code");

        return kakaoLogin.toString();
    }

    private final RestTemplate restTemplate;

    @Override
    public KakaoDTO getKakaoInfo(String code) throws Exception {
        if (code == null) throw new Exception("Failed get authorization code");

        String accessToken = "";
        String refreshToken = "";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", KAKAO_CLIENT_ID);
            params.add("client_secret", KAKAO_CLIENT_SECRET);
            params.add("code", code);
            params.add("redirect_uri", KAKAO_REDIRECT_URL);

            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_AUTH_URI + "/oauth/token",
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
            throw new Exception("API Call failed Exception");
        }

        return getUserInfoWithToken(accessToken);
    }

    @Override
    public KakaoDTO getUserInfoWithToken(String accessToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_API_URI + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
        JSONObject account = (JSONObject) jsonObject.get("kakao_account");
        JSONObject profile = (JSONObject) jsonObject.get("profile");

        System.err.println(account);

        long id = (long) jsonObject.get("id");
        String email = String.valueOf(account.get("email"));
        String gender = String.valueOf(account.get("gender"));
        String name = String.valueOf(account.get("name"));
        String ageRange = String.valueOf(account.get("age_range"));
        String birthday = String.valueOf(account.get("birthday"));
        String birthyear = String.valueOf(account.get("birthyear"));
        String nickname = String.valueOf(account.get("profile_nickname_needs_agreement"));

        return KakaoDTO.builder()
                .email(email)
                .nickname(nickname)
                .ageRange(ageRange)
                .gender(gender)
                .name(name)
                .birthday(birthday)
                .birthyear(birthyear)
                .build();
    }
}
