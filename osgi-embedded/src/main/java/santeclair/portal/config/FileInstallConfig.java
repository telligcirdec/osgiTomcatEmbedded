package santeclair.portal.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileInstallConfig implements InitConfig {

    private String rootDir;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileInstallConfig.class);

    public FileInstallConfig(String rootDir) {
        LOGGER.debug("Construct FileInstallConfig with rootDir : "
                        + rootDir);
        this.rootDir = rootDir;
    }

    public Map<String, Object> getInitConfig() {
        LOGGER.debug("init FileInstallConfig");
        Map<String, Object> fileInstallConfig = new HashMap<>();

        fileInstallConfig
                        .put("felix.fileinstall.dir", rootDir + "/auto-bundle");
        fileInstallConfig.put("felix.fileinstall.tmpdir", rootDir
                        + "/auto-bundle/tmp");
        fileInstallConfig.put("felix.fileinstall.log.level", "4");

        return fileInstallConfig;
    }

}
