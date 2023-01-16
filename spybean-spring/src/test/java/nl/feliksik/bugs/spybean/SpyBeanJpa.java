package nl.feliksik.bugs.spybean;

import nl.feliksik.bugs.spybean.jpa.EntityJpaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;


@DataJpaTest
@ContextConfiguration(classes = SpyBeanJpa.MyConfig.class)
@SpyBean(classes = EntityJpaRepo.class)
class SpyBeanJpa {

    @Configuration("TestService")
    @EnableJpaRepositories({"nl.feliksik.bugs.spybean.jpa"})
    @EntityScan({"nl.feliksik.bugs.spybean.jpa"})
    @ComponentScan({
            "nl.feliksik.bugs.spybean",
    })
    public static class MyConfig {
    }

    @Autowired
    private MyService systemUnderTest;

    @Autowired
    private EntityJpaRepo jpaRepo;

    @BeforeEach
    void setUp() {
        // workaround for https://github.com/spring-projects/spring-boot/issues/33830
        //Mockito.reset(jpaRepo);
    }

    @Test
    void testA_break() {
        RuntimeException runtimeException = new RuntimeException("We simulate a failure");

        // Note that if you use the syntax that starts with when(), the real method
        // is called with null param. This is an unrelated issue I don't understand.
        doThrow(runtimeException).when(jpaRepo).saveAndFlush(any());

        try {
            systemUnderTest.upsert("someId");

            // should not reach this
            assertThat(true).isFalse();
        } catch (RuntimeException e) {
            // ok
            assertThat(e).isEqualTo(runtimeException);
        }
    }


    @Test
    void testB_ok() {
        // should be ok -- but it FAILS!
        systemUnderTest.upsert("someId");
    }
}