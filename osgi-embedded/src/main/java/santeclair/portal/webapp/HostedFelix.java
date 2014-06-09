package santeclair.portal.webapp;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import santeclair.portal.FelixLauncher;

/**
 * Servlet implementation class HostedFelix
 */
@Component
public class HostedFelix implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2799328988763863564L;

    private static final Logger LOGGER = LoggerFactory.getLogger(HostedFelix.class);

    @Autowired
    private FelixLauncher felixLauncher;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HostedFelix() {
        LOGGER.debug("Construct HostedFelix");
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    @PostConstruct
    public void init() throws ServletException {
        LOGGER.debug("HostedFelix.init");
        felixLauncher.start();
    }

    @PreDestroy
    public void destroy() {
        LOGGER.debug("HostedFelix.destroy");
        felixLauncher.stop();
    }

}
