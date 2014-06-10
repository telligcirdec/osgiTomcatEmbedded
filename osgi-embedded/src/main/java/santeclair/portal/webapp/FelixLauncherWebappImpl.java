package santeclair.portal.webapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import santeclair.portal.AbstractFelixLauncher;
import santeclair.portal.config.FileInstallConfig;
import santeclair.portal.config.FrameworkConfig;
import santeclair.portal.config.InitConfig;
import santeclair.portal.config.WebEmbeddedConfig;

@Component
public class FelixLauncherWebappImpl extends AbstractFelixLauncher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -742616636242601752L;

	private static final Logger LOGGER = LoggerFactory.getLogger(FelixLauncherWebappImpl.class);

	@Autowired
	@Qualifier("felixProperties")
	private Properties props;

	@Value("#{felixProperties['felixlaucher.root.dir']}")
	private String rootDir;

	@Autowired
	private HostActivator hostActivator;

	public Bundle[] getInstalledBundles() {
		LOGGER.debug("FelixLauncherWebappImpl.getInstalledBundles");
		if (hostActivator != null) {
			// Use the system bundle activator to gain external
			// access to the set of installed bundles.
			return hostActivator.getBundles();
		} else {
			throw new IllegalStateException("hostActivator is not initialized.");
		}
	}

	@Override
	protected List<InitConfig> getInitConfigList() {
		LOGGER.debug("FelixLauncherWebappImpl.getInitConfigList()");
		List<InitConfig> initConfigList = new ArrayList<>();

		LOGGER.debug("getInitConfigList=>props : " + props);

		initConfigList.add(new FrameworkConfig(rootDir, props));
		initConfigList.add(new WebEmbeddedConfig(hostActivator));
		initConfigList.add(new FileInstallConfig(rootDir));

		return initConfigList;
	}

}
