package uk.firedev.emfpinata.config;

import com.oheers.fish.config.ConfigBase;
import uk.firedev.emfpinata.EMFPinata;

public class ExampleConfig extends ConfigBase {

    public ExampleConfig() {
        super("examples.yml", "examples.yml", EMFPinata.getInstance(), false);
        defaultFile();
    }

    private void defaultFile() {
        getConfig().getFile().delete();
        reload();
    }

}
