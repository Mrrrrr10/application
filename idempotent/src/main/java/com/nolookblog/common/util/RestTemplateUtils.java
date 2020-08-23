package com.nolookblog.common.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.HttpURLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author Mrrrrr10
 * @description spring REST_TEMPLATE
 */

public class RestTemplateUtils {

	private static final RestTemplate REST_TEMPLATE = new RestTemplate();
	private static final RestTemplate REST_TEMPLATE_SSL = new RestTemplate(new HttpsClientRequestFactory());

	// ----------------------------------GET-------------------------------------------------------

	/**
	 * GET请求调用方式
	 *
	 * @param url          请求URL
	 * @param responseType 返回对象类型
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, Class<T> responseType) {
		return REST_TEMPLATE.getForEntity(url, responseType);
	}

	/**
	 * GET请求调用方式
	 *
	 * @param url          请求URL
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, Class<T> responseType, Object... uriVariables) {
		return REST_TEMPLATE.getForEntity(url, responseType, uriVariables);
	}

	/**
	 * GET请求调用方式
	 *
	 * @param url          请求URL
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, Class<T> responseType, Map<String, ?> uriVariables) {
		return REST_TEMPLATE.getForEntity(url, responseType, uriVariables);
	}

	/**
	 * 带请求头的GET请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, Map<String, String> headers, Class<T> responseType, Object... uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return get(url, httpHeaders, responseType, uriVariables);
	}

	/**
	 * 带请求头的GET请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> responseType, Object... uriVariables) {
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		return exchange(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的GET请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, Map<String, String> headers, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return get(url, httpHeaders, responseType, uriVariables);
	}

	/**
	 * 带请求头的GET请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		return exchange(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
	}

	// ----------------------------------POST-------------------------------------------------------

	/**
	 * POST请求调用方式
	 *
	 * @param url          请求URL
	 * @param responseType 返回对象类型
	 * @return
	 */
	public static <T> ResponseEntity<T> post(String url, Class<T> responseType) {
		return REST_TEMPLATE.postForEntity(url, HttpEntity.EMPTY, responseType);
	}

	/**
	 * POST请求调用方式
	 *
	 * @param url          请求URL
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType) {
		return REST_TEMPLATE.postForEntity(url, requestBody, responseType);
	}

	/**
	 * POST请求调用方式
	 *
	 * @param url          请求URL
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> postWithSsl(String url, Object requestBody, Class<T> responseType) {
		return REST_TEMPLATE_SSL.postForEntity(url, requestBody, responseType);
	}

	/**
	 * POST请求调用方式
	 *
	 * @param url          请求URL
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType, Object... uriVariables) {
		return REST_TEMPLATE.postForEntity(url, requestBody, responseType, uriVariables);
	}

	/**
	 * POST请求调用方式
	 *
	 * @param url          请求URL
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
		return REST_TEMPLATE.postForEntity(url, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的POST请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return post(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的POST请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return post(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的POST请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return post(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的POST请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return post(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的POST请求调用方式
	 *
	 * @param url           请求URL
	 * @param requestEntity 请求头和请求体封装对象
	 * @param responseType  返回对象类型
	 * @param uriVariables  URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
		return REST_TEMPLATE.exchange(url, HttpMethod.POST, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的POST请求调用方式
	 *
	 * @param url           请求URL
	 * @param requestEntity 请求头和请求体封装对象
	 * @param responseType  返回对象类型
	 * @param uriVariables  URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) {
		return REST_TEMPLATE.exchange(url, HttpMethod.POST, requestEntity, responseType, uriVariables);
	}

	// ----------------------------------PUT-------------------------------------------------------

	/**
	 * PUT请求调用方式
	 *
	 * @param url          请求URL
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, Class<T> responseType, Object... uriVariables) {
		return put(url, HttpEntity.EMPTY, responseType, uriVariables);
	}

	/**
	 * PUT请求调用方式
	 *
	 * @param url          请求URL
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, Object requestBody, Class<T> responseType, Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
		return put(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * PUT请求调用方式
	 *
	 * @param url          请求URL
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
		return put(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的PUT请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return put(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的PUT请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return put(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的PUT请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return put(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的PUT请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return put(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的PUT请求调用方式
	 *
	 * @param url           请求URL
	 * @param requestEntity 请求头和请求体封装对象
	 * @param responseType  返回对象类型
	 * @param uriVariables  URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
		return REST_TEMPLATE.exchange(url, HttpMethod.PUT, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的PUT请求调用方式
	 *
	 * @param url           请求URL
	 * @param requestEntity 请求头和请求体封装对象
	 * @param responseType  返回对象类型
	 * @param uriVariables  URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) {
		return REST_TEMPLATE.exchange(url, HttpMethod.PUT, requestEntity, responseType, uriVariables);
	}

	// ----------------------------------DELETE-------------------------------------------------------

	/**
	 * DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Class<T> responseType, Object... uriVariables) {
		return delete(url, HttpEntity.EMPTY, responseType, uriVariables);
	}

	/**
	 * DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Class<T> responseType, Map<String, ?> uriVariables) {
		return delete(url, HttpEntity.EMPTY, responseType, uriVariables);
	}

	/**
	 * DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Object requestBody, Class<T> responseType, Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Class<T> responseType, Object... uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return delete(url, httpHeaders, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Class<T> responseType, Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return delete(url, httpHeaders, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return delete(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return delete(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 *
	 * @param url          请求URL
	 * @param headers      请求头参数
	 * @param requestBody  请求参数体
	 * @param responseType 返回对象类型
	 * @param uriVariables URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的DELETE请求调用方式
	 *
	 * @param url           请求URL
	 * @param requestEntity 请求头和请求体封装对象
	 * @param responseType  返回对象类型
	 * @param uriVariables  URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
		return REST_TEMPLATE.exchange(url, HttpMethod.DELETE, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的DELETE请求调用方式
	 *
	 * @param url           请求URL
	 * @param requestEntity 请求头和请求体封装对象
	 * @param responseType  返回对象类型
	 * @param uriVariables  URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) {
		return REST_TEMPLATE.exchange(url, HttpMethod.DELETE, requestEntity, responseType, uriVariables);
	}

	// ----------------------------------通用方法-------------------------------------------------------

	/**
	 * 通用调用方式
	 *
	 * @param url           请求URL
	 * @param method        请求方法类型
	 * @param requestEntity 请求头和请求体封装对象
	 * @param responseType  返回对象类型
	 * @param uriVariables  URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
		return REST_TEMPLATE.exchange(url, method, requestEntity, responseType, uriVariables);
	}

	/**
	 * 通用调用方式
	 *
	 * @param url           请求URL
	 * @param method        请求方法类型
	 * @param requestEntity 请求头和请求体封装对象
	 * @param responseType  返回对象类型
	 * @param uriVariables  URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) {
		return REST_TEMPLATE.exchange(url, method, requestEntity, responseType, uriVariables);
	}

	/**
	 * 获取RestTemplate实例对象，可自由调用其方法
	 *
	 * @return RestTemplate实例对象
	 */
	public static RestTemplate getRestTemplate() {
		return REST_TEMPLATE;
	}

	/**
	 * 获取带SSL认证的RestTemplate实例对象，可自由调用其方法
	 *
	 * @return RestTemplate实例对象
	 */
	public static RestTemplate getRestTemplateWithSSL() {
		return REST_TEMPLATE_SSL;
	}
}

class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {

	@Override
	protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
		try {
			if (!(connection instanceof HttpsURLConnection)) {// http协议
				//throw new RuntimeException("An instance of HttpsURLConnection is expected");
				super.prepareConnection(connection, httpMethod);
			}
			if (connection instanceof HttpsURLConnection) {// https协议，修改协议版本
				SSLContext ctx = SSLContext.getInstance("TLSv1.2");
				X509TrustManager tm = new X509TrustManager() {
					@Override
					public void checkClientTrusted(X509Certificate[] chain,
												   String authType) throws CertificateException {
					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain,
												   String authType) throws CertificateException {
					}

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
				};
				ctx.init(null, new TrustManager[]{tm}, null);
				org.apache.http.conn.ssl.SSLSocketFactory ssf = new org.apache.http.conn.ssl.SSLSocketFactory(ctx, org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				((HttpsURLConnection) connection).setSSLSocketFactory(ctx.getSocketFactory());
				HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;

				super.prepareConnection(httpsConnection, httpMethod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
