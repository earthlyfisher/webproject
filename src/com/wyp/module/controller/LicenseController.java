package com.wyp.module.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wyp.module.pojo.License;

@Controller
@RequestMapping("/license")
public class LicenseController {

	private Map<String, String> map = new HashMap<String, String>();

	@RequestMapping("generatelicense")
	public void generatelicense(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException, IOException {
		Properties properties = new Properties();
		String responseJson = "";
		try {
			properties.load(session.getServletContext().getResourceAsStream("/WEB-INF/classes/citrixInfo.properties"));
		} catch (IOException e) {
			responseJson = "找不到citrixInfo.properties文件";
			repsonseJsonResult(response, responseJson, "json");
			return;
		}
		initProperties(properties);
		String address = map.get("address");
		String version = map.get("version");
		String oemKey = map.get("oem_key");
		String secret = map.get("secret");
		String sockets=map.get("sockets");
		String editon=map.get("editon");
		if (null == address || "".equals(address.trim())) {
			responseJson = "citrix webservice地址为空";
			repsonseJsonResult(response, responseJson, "json");
			return;
		}
		if (null == version || "".equals(version.trim())) {
			responseJson = "version为空";
			repsonseJsonResult(response, responseJson, "json");
			return;
		}
		if (null == oemKey || "".equals(oemKey.trim())) {
			responseJson = "oem_key为空";
			repsonseJsonResult(response, responseJson, "json");
			return;
		}
		if (null == secret || "".equals(secret.trim())) {
			responseJson = "secret为空";
			repsonseJsonResult(response, responseJson, "json");
			return;
		}
		if (null == sockets || "".equals(sockets.trim())) {
			responseJson = "sockets为空";
			repsonseJsonResult(response, responseJson, "json");
			return;
		}
		if (null == editon || "".equals(editon.trim())) {
			responseJson = "editon为空";
			repsonseJsonResult(response, responseJson, "json");
			return;
		}
		License license = new License();
		license.setName("inspur");
		license.setAddress1("");
		license.setAddress2("浪潮信息");
		license.setCity("");
		license.setState("inspur");
		license.setPostalcode("");
		license.setCompany("");
		license.setCountry("");
		license.setEditon(sockets);// premium，premium-trial
		license.setSockets(editon);
		license.setRequestUUID(UUID.randomUUID().toString());
		String requestJson = assembRequestJson(license);
		// 生成license
		String licenses = getCitrixLicense(requestJson);
		if ("".equals(licenses)) {
			responseJson = "实时生成license错误";
			repsonseJsonResult(response, responseJson, "json");
			return;
		}
		if (licenses.equals("")) {
			getCitrixLicense(requestJson);
		}
		repsonseJsonResult(response, licenses, "text");
	}

	/**
	 * 组装requsetjson
	 * 
	 * @return
	 */
	private String assembRequestJson(License license) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"requestUUID\":");
		sb.append("\"" + license.getRequestUUID() + "\"");
		sb.append(",\"version\":");
		sb.append("\"" + map.get("version") + "\"");
		sb.append(",\"oem_key\":");
		sb.append("\"" + map.get("oem_key") + "\"");
		sb.append(",\"secret\":");
		sb.append("\"" + map.get("secret") + "\"");
		sb.append(",\"customer_info\":{");
		sb.append("\"name\":");
		sb.append("\"" + license.getName() + "\"");
		sb.append(",\"address_1\":");
		sb.append("\"" + license.getAddress1() + "\"");
		sb.append(",\"address_2\":");
		sb.append("\"" + license.getAddress2() + "\"");
		sb.append(",\"city\":");
		sb.append("\"" + license.getCity() + "\"");
		sb.append(",\"state\":");
		sb.append("\"" + license.getState() + "\"");
		sb.append(",\"postalcode\":");
		sb.append("\"" + license.getPostalcode() + "\"");
		sb.append(",\"country\":");
		sb.append("\"" + license.getCountry() + "\"");
		sb.append(",\"company\":");
		sb.append("\"" + license.getCompany() + "\"},");
		sb.append("\"edition\":");
		sb.append("\"" + license.getEditon() + "\"");
		sb.append(",\"sockets\":");
		sb.append("\"" + license.getSockets() + "\"}");
		return sb.toString();
	}

	/**
	 * 连接ws返回license串
	 * 
	 * @param properties
	 * @return
	 */
	private String getCitrixLicense(String requestJson) {
		String licenses = "";
		OutputStreamWriter out = null;
		InputStream is = null;
		long start = System.currentTimeMillis();
		try {
			// ws url
			URL url = new URL(map.get("address"));
			// ws connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type", " application/json");
			connection.setRequestMethod("POST");
			connection.connect();
			out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			out.append(requestJson);
			out.flush();
			// response string
			is = connection.getInputStream();
			int length = is.available();
			if (0 < length) {
				long end = System.currentTimeMillis();
				System.out.println("成功" + (end - start));
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				StringBuilder tempStr = new StringBuilder();
				String temp = "";
				while ((temp = reader.readLine()) != null) {
					tempStr.append(temp);
				}
				licenses = tempStr.toString(); // utf-8编码
				System.out.println(licenses);
			}
		} catch (IOException e) {
			String eMsg = e.getMessage();
			if ("Read timed out".equals(eMsg) || "Connection timed out: connect".equals(eMsg)
					|| "Software caused connection abort: recv failed".equals(eMsg)) {
				long end = System.currentTimeMillis();
				System.out.println(eMsg + (end - start));
				licenses = "TIMEOUT";
			}
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(licenses);
		return licenses;
	}

	/**
	 * 解析license串
	 * 
	 * @param licenses
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Object> parseLicenses(String licenses) {
		System.out.println(licenses);
		Map<String, Object> map = (Map<String, Object>) JSONObject.parse(licenses);
		if (null == map) {
			return new ArrayList<Object>();
		}
		String sourceIP = (String) map.get("sourceIP");
		String requestUUID = (String) map.get("requestUUID");
		String version = (String) map.get("version");
		String created = (String) map.get("created");
		System.out.println("source IP: " + sourceIP + ", requestUUID:" + requestUUID + ",version: " + version
				+ ",created" + created);
		List<Object> array = JSONArray.parseArray(map.get("payload").toString());
		return array;
	}

	/**
	 * 初始设置
	 * 
	 * @param properties
	 */
	private void initProperties(Properties properties) {
		Set<Entry<Object, Object>> set = properties.entrySet();
		if (null != set) {
			for (Entry<Object, Object> entry : set) {
				map.put(entry.getKey().toString(), entry.getValue().toString());
			}
		}
	}

	/**
	 * 
	 * @param response
	 * @param responseJson
	 * @param type
	 */
	private void repsonseJsonResult(HttpServletResponse response,
			String responseJson, String type) {
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		ServletOutputStream outStr = null;
		try {
			outStr = response.getOutputStream();// 建立
			buff = new BufferedOutputStream(outStr);
			// 通过类型判断下载还是返回数据json、text
			if ("json".equals(type)) {
				response.setContentType("application/json");// 一下两行关键的设置
				write.append(responseJson);
			} else {
				// ** 下载
				response.setContentType("text/plain");// 一下两行关键的设置
				response.addHeader("Content-Disposition",
						"attachment;filename=license.asc");// filename指定默认的名字O
				String enter = "\r\n";
				// json解析
				List<Object> list = parseLicenses(responseJson);
				for (int i = 0; i < list.size(); i++) {
					write.append(list.get(i));
					write.append(enter);
				}
			}
			buff.write(write.toString().getBytes("UTF-8"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outStr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String generateCitrixLicense(HttpSession session, License license) {
		Properties properties = new Properties();
		String responseJson = "";
		try {
			properties.load(session.getServletContext().getResourceAsStream(
					"/WEB-INF/classes/citrixInfo.properties"));
		} catch (IOException e) {
			return "找不到citrixInfo.properties文件";
		}
		initProperties(properties);
		String address = map.get("address");
		String version = map.get("version");
		String oemKey = map.get("oem_key");
		String secret = map.get("secret");
		if (null == address || "".equals(address.trim())) {
			return "citrix webservice地址为空";
		}
		if (null == version || "".equals(version.trim())) {
			return "version为空";
		}
		if (null == oemKey || "".equals(oemKey.trim())) {
			return "oem_key为空";
		}
		if (null == secret || "".equals(secret.trim())) {
			return "secret为空";
		}
		license.setName("inspur");
		license.setAddress1("");
		license.setAddress2("浪潮信息");
		license.setCity("");
		license.setState("inspur");
		license.setPostalcode("");
		license.setCompany("");
		license.setCountry("");
		license.setEditon("premium");// premium
		license.setSockets("56");
		license.setRequestUUID(UUID.randomUUID().toString());

		// 生成请求json串
		String requestJson = assembRequestJson(license);
		// 生成license串
		String licenses = getCitrixLicense(requestJson);
		if ("TIMEOUT".equals(licenses)) {
			licenses = getCitrixLicense(requestJson);
		}

		return responseJson;
	}

	public static void main(String[] args) {
		StringBuilder sb=new StringBuilder();
		sb.append("1237275897295725");
		String str=sb.toString();
		System.out.println(str);
	}
}
