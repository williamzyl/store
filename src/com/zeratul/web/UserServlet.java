package com.zeratul.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.zeratul.bean.User;
import com.zeratul.service.UserService;
import com.zeratul.utils.CommonUtils;
import com.zeratul.utils.MailUtils;

public class UserServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5167655563902152242L;

	public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		UserService service = new UserService();
		boolean isExist = service.checkName(username);

		String json = "{\"isExist\":" + isExist + "}";
		response.getWriter().write(json);
	}

	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String[]> params = request.getParameterMap();

		User user = new User();

		try {
			ConvertUtils.register(new Converter() {

				@Override
				public Object convert(Class clazz, Object value) {

					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/DD");
					Date parse = null;
					try {
						parse = format.parse(value.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}

					return parse;
				}
			}, Date.class);

			BeanUtils.populate(user, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		user.setUid(CommonUtils.getUUid());
		System.out.println(user.getUid());
		user.setState(0);
		String activeCode = CommonUtils.getUUid();
		user.setCode(activeCode);
		user.setTelephone("");

		UserService register = new UserService();
		boolean isRegisterSuccess = register.register(user);

		if (isRegisterSuccess) {

			String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户" + "<a href='http://localhost:8888/Store/user?method=active&activeCode="
					+ activeCode + "'>" + "http://localhost:8888/Store/user?method=active&activeCode=" + activeCode + "</a>";

			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}

			response.sendRedirect(request.getContextPath() + "/registerSuccess.jsp");

		} else {
			response.sendRedirect(request.getContextPath() + "/registerFail.jsp");

		}
	}

	public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String activeCode = request.getParameter("activeCode");

		UserService service = new UserService();
		try {

			service.activeUser(activeCode);

			response.sendRedirect(request.getContextPath() + "/login.jsp");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}