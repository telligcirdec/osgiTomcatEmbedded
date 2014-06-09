package santeclair.portal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.felix.framework.Felix;
import org.apache.felix.main.AutoProcessor;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import santeclair.portal.config.InitConfig;

public abstract class AbstractFelixLauncher implements FelixLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFelixLauncher.class);

    protected Felix felix;

    @Override
    public void start() {
        LOGGER.debug("AbstractFelixLauncher.start");
        try {
            Map<String, Object> frameworkInitMap = initConfigMap();

            felix = new Felix(frameworkInitMap);
            felix.init();
            AutoProcessor.process(frameworkInitMap, felix.getBundleContext());
            felix.start();

            LOGGER.debug("\n*********************\n* Installed bundle\n*********************");
            for (Bundle installedBundle : getInstalledBundles()) {
                LOGGER.debug(installedBundle.getBundleId() + " : "
                                + installedBundle.getSymbolicName());
            }

        } catch (BundleException ex) {
            LOGGER.error("Could not create framework: " + ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void stop() {
        LOGGER.debug("AbstractFelixLauncher.stop");
        try {
            if (felix != null) {
                felix.stop();
                felix.waitForStop(0);
            } else {
                throw new IllegalStateException(
                                "Cant stop framework not started.");
            }
        } catch (BundleException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private Map<String, Object> initConfigMap() {
        LOGGER.debug("AbstractFelixLauncher.initConfigMap");
        Map<String, Object> initConfigMap = new HashMap<>();
        for (InitConfig initConfig : getInitConfigList()) {
            Map<String, Object> initConfigMapFromList = initConfig.getInitConfig();
            initConfigMap.putAll(initConfigMapFromList);
        }
        return initConfigMap;
    }

    protected abstract List<InitConfig> getInitConfigList();
}
