package santeclair.portal.webapp;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import santeclair.portal.osgi.service.log.ExtendedLogReaderService;
import santeclair.portal.osgi.service.log.ExtendedLogService;
import santeclair.portal.osgi.service.log.internal.ExtendedLogReaderServiceFactory;
import santeclair.portal.osgi.service.log.internal.ExtendedLogServiceFactory;

@Component
public class HostActivator implements BundleActivator {

    private static final Logger LOGGER = LoggerFactory.getLogger(HostActivator.class);

    private BundleContext bundleContext = null;

    public void start(BundleContext bundleContext) {
        LOGGER.debug("Starting HostActivator");
        this.bundleContext = bundleContext;
        registerExtendedLogService();
    }

    public void stop(BundleContext bundleContext) {
        LOGGER.debug("Stopping HostActivator");
        this.bundleContext = null;
    }

    private void registerExtendedLogService() {
        ExtendedLogReaderServiceFactory extendedLogReaderServiceFactory = new ExtendedLogReaderServiceFactory();
        bundleContext.registerService(ExtendedLogReaderService.class.getName(), extendedLogReaderServiceFactory, null);
        ExtendedLogServiceFactory extendedLogServiceFactory = new ExtendedLogServiceFactory(extendedLogReaderServiceFactory);
        bundleContext.registerService(ExtendedLogService.class.getName(), extendedLogServiceFactory, null);
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
