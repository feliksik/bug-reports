package nl.feliksik.dropwizardproblem.serverapi.resources;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class RunsResourceTest {
    public static final ResourceExtension FIXTURE = ResourceExtension.builder()
            .addResource(new HelloResource())
            .build();

    @BeforeEach
    void setup() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testHi() {
        Response response = FIXTURE.target("/mypath").request().get();
        String responseBody = response.readEntity(String.class);

        assertEquals("hi", responseBody);
    }

}
