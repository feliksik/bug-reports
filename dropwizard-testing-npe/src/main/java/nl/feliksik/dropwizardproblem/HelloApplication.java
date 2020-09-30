package nl.feliksik.dropwizardproblem;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.feliksik.dropwizardproblem.serverapi.resources.HelloResource;

public class HelloApplication extends Application<HelloApplicationConfig> {

    public static void main(final String[] args) throws Exception {
        new HelloApplication().run(args);
    }

    @Override
    public String getName() {
        return "myHelloApplication";
    }

    @Override
    public void initialize(final Bootstrap<HelloApplicationConfig> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final HelloApplicationConfig configuration,
                    final Environment environment) {
        environment.jersey().register(HelloResource.class);
    }

}
