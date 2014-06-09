package santeclair.portal;

import org.osgi.framework.Bundle;

public interface FelixLauncher {

	void start();
	
	void stop();
	
	Bundle[] getInstalledBundles();
	
}
