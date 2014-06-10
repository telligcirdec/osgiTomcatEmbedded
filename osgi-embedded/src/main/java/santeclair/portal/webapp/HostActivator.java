package santeclair.portal.webapp;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HostActivator implements BundleActivator {

    private static final Logger LOGGER = LoggerFactory.getLogger(HostActivator.class);

    private BundleContext bundleContext = null;

    public void start(BundleContext bundleContext) {
        LOGGER.debug("Starting HostActivator");
        this.bundleContext = bundleContext;
    }

    public void stop(BundleContext bundleContext) {
        LOGGER.debug("Stopping HostActivator");
        this.bundleContext = null;
    }

    public Bundle[] getBundles() {
        LOGGER.debug("HostActivator.getBundles");
        if (bundleContext != null) {
            return bundleContext.getBundles();
        }
        return null;
    }

    public BundleContext getBundleContext() {
        LOGGER.debug("HostActivator.getBundleContext");
        return bundleContext;
    }
}
