package com.cqsd.net.controller;

import com.cqsd.data.utils.TokenManager;
import com.cqsd.net.entry.TokenInfo;
import com.cqsd.util.QRCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author caseycheng
 * @date 2022/11/12-14:04
 **/
@RestController
@RequestMapping("/api/v1/employee")
@Slf4j
public class QRController {
	@Autowired
	ServletContext context;
	
	@GetMapping("/qr")
	public void createQR(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置图片头
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		final var remoteHost = request.getRemoteHost();
		final var remoteAddr = request.getRemoteAddr();
		final var requestURL = request.getRequestURL();
		var url = requestURL.toString();
		final var hash = hash(remoteAddr, remoteHost);
		//在这里返回二维码图片
		String encode = String.format("http://%s/api/v1/employee/login?t=%s", url.split("/")[2],hash);
		log.debug("唯一码为{}", hash);
		//生成待验证用户,默认login情况为false
		TokenManager.createVerified(hash, new TokenInfo());
//		JsonUtil.writeJson(response.getWriter(), hash);
		QRCodeUtils.witeImage(encode,response.getOutputStream());
	}
	
	public final long hash(String... resource) {
		long ret = 0;
		for (String s : resource) {
			final var code = s.hashCode();
			ret += code;
		}
		return ret << 16;
	}
	
}
