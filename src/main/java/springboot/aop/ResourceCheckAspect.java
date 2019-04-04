package springboot.aop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ch.qos.logback.core.net.SyslogOutputStream;
import springboot.service.IdealService;
import springboot.service.PlayerService;
import springboot.service.UserService;

@Aspect
@Configuration
public class ResourceCheckAspect {
	@Autowired
	private UserService userService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private IdealService idealService;

	private Logger logger = LoggerFactory.getLogger(ResourceCheckAspect.class);

//	@Before("execution(* springboot.web.*.*(..))")
//	public void checkResource(JoinPoint joinPoint) {
//		
//		// Just check invalid path
//		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
//				.getRequestAttributes();
//		HttpServletRequest request = requestAttributes.getRequest();
//		HttpServletResponse response = requestAttributes.getResponse();
//
//		String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
//		String[] path = request.getRequestURI().split("/");
//		String id = path[path.length - 1];
//		String methodName = joinPoint.getSignature().getName();
//		boolean error = false;
//
//		if ("springboot.web.PageErrorController".equals(declaringTypeName)) {
//			return;
//		}
//
//		logger.info("======Check Resources======");
//		if ("springboot.web.PlayerController".equals(declaringTypeName)) {
//			// Check Player
//			logger.info(">>>Check Player");
//			if (!playerService.isExist(id) && !"getPlayers".equals(methodName)) {
//				error = true;
//			}
//		} else if ("springboot.web.UserController".equals(declaringTypeName)) {
//			// Check User
//			logger.info(">>>Check User");
//			if (!userService.isExistById(id) && ("getUserById".equals(methodName) || "updateUser".equals(methodName))) {
//				error = true;
//			}
//		} else if ("springboot.web.IdealController".equals(declaringTypeName)) {
//			// Check Ideal
//			logger.info(">>>Check Ideal");
//			if (!idealService.isExist(id) && "initialIdeal".equals(methodName)) {
//				error = true;
//			}
//		}
//
//		if (error) {
//			try {
//				request.getRequestDispatcher("/404").forward(request, response);
//				// TODO forward: the program will continue
//			} catch (ServletException | IOException e) {
//				e.printStackTrace();
//			}
//		}
//		logger.info("======Check Resources End======");
//	}
}
