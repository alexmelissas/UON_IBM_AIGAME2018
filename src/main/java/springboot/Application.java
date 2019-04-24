package springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * This Application class is the starter of the SpringBoot.
 * </p>
 * 
 * @author chenyu
 *
 */
@SpringBootApplication
public class Application {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("======Applicatiton is running======");
	}

	/**
	 * Handle the error page
	 * @return a WebServerFactoryCustomizer bean
	 */
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
		return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
			@Override
			public void customize(ConfigurableWebServerFactory factory) {
				factory.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/404"));
				factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
				factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
			}
		};
	}
}
