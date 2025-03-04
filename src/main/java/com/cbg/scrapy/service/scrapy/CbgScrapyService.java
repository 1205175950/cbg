package com.cbg.scrapy.service.scrapy;

import com.alibaba.fastjson.JSONObject;
import com.cbg.scrapy.service.dto.EquipParseDto;
import com.cbg.scrapy.service.dto.RequestParseDto;
import com.cbg.scrapy.service.dto.ResponseParseDto;
import com.cbg.scrapy.service.exception.CbgBizException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CbgScrapyService {

    private static final int OK_STATUS = 200;
    private static final HttpClient client = HttpClient.newHttpClient();

    private static URI TARGET_URL = null;

    static {
        try {
            TARGET_URL = new URI("https://xyq-m.cbg.163.com/cgi/api/get_equip_detail");
        } catch (URISyntaxException e) {
            log.error("create URI error", e);
            CbgBizException.fly("创建URI出现异常");
        }
    }

    /**
     * 根据用户商品链接获取商品详情信息
     * @param url         商品链接
     * @return            商品详情信息
     */
    public EquipParseDto getSellEquipInfo(String url) {
        if (StringUtils.isEmpty(url)) {
            CbgBizException.fly("解析商品链接异常，请重新填写正确的商品链接");
        }
        HttpResponse<String> response = sendPostRequest(url);
        if (null == response || OK_STATUS != response.statusCode() || StringUtils.isEmpty(response.body())) {
            CbgBizException.fly("获取商品详情异常，请尝试请求后联系管理员");
        }
        ResponseParseDto parseDto = JSONObject.parseObject(response.body(), ResponseParseDto.class);
        if (parseDto.getStatus() == 0) {
            CbgBizException.fly(parseDto.getMsg());
        }
        return parseDto.getEquip();

    }

    /**
     * 根据商品链接获得网易请求结果
     * @param url         商品链接
     * @return            结果json字符串
     */
    public HttpResponse<String> sendPostRequest(String url) {
        // 1. 解析URL中的eid和serviceId
        RequestParseDto parseDto = parseUrl(url);
        if (null == parseDto || null == parseDto.getEid() || null == parseDto.getServerId()) {
            CbgBizException.fly("解析商品链接异常，请重新填写正确的商品链接");
        }
        try {
            // 2. 构建POST请求的form-data
            Map<Object, Object> formData = new HashMap<>();
            formData.put("eid", parseDto.getEid());
            formData.put("serverid", parseDto.getServerId());
            // 3. 构建HTTP请求
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(TARGET_URL)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(formData)))
                    .build();
            // 4. 发送请求并获取响应
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            log.error("sendPostRequest occurs error, url: {}", url, e);
        }
        return null;
    }

    /**
     * 验证账号有效性，进行登录尝试
     * @param userName 尝试登录的用户名
     * @param password 尝试登录的密码
     */
    public boolean trySignIn(String userName, String password) {
        // TODO
        return true;
    }

    /**
     * 解析商品链接中的流水号和服务器id
     * @param url 原始商品链接
     * @return    解析结果
     */
    private static RequestParseDto parseUrl(String url) {

        // 正则表达式匹配
        String pattern = "^(.+)\\?s=([0-9]+)&eid=(.*)&(.*)$";
        Pattern regexp = Pattern.compile(pattern);
        Matcher matcher = regexp.matcher(url);
        try {
            if (matcher.find()) {
                RequestParseDto requestParseDto = new RequestParseDto();
                requestParseDto.setUrl(matcher.group(1));
                requestParseDto.setServerId(Integer.valueOf(matcher.group(2)));
                requestParseDto.setEid(matcher.group(3));
                return requestParseDto;
            }
        } catch (Exception e) {
            log.error("parse url occurs error, url: {}", url, e);
            CbgBizException.fly("解析商品链接异常，请重新填写正确的商品链接");
        }
        return null;
    }

    /**
     * fomData数据转换成字符串拼接形式
     * @param formData 表单数据
     * @return         拼接后的字符串
     */
    private static String getFormDataAsString(Map<Object, Object> formData) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Object, Object> entry : formData.entrySet()) {
            if (!result.isEmpty()) {
                result.append("&");
            }
            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }
        return result.toString();
    }
}
