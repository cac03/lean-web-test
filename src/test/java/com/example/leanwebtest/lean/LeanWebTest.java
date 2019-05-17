package com.example.leanwebtest.lean;

import com.example.leanwebtest.web.config.MvcConfig;
import com.example.leanwebtest.web.config.WebSecurityConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for tests running against full blown server to test
 * correctness of server configuration.
 * <p>
 * One might for example:
 * <ul>
 * <li>Test if security framework does not prevent serving static content
 * </li>
 * <li>Check correctness of {@code robots.txt} file</li>
 * <li>Check configuration of supplied {@link javax.servlet.Filter}</li>
 * <li>Check if static resources are correctly compressed</li>
 * <li>Check configuration of used server</li>
 * </ul>
 * <p>
 * It is expected that test classes annotated with {@link LeanWebTest} will
 * autowire {@link org.springframework.boot.test.web.client.TestRestTemplate}
 * or maybe {@code TestWebClient}
 * <p>
 * Note that this is just a rough demonstration.
 * It applies configurations referenced by the application.
 * <p>
 * For a general case the {@link LeanWebTest} should be heavily modified
 * <p>
 * We're disabling some auto-configuration classes here directly referencing them
 * This approach might be applicable for simple use-cases.
 * A general solution might involve using {@link org.springframework.boot.context.TypeExcludeFilter} instead
 *
 * @see com.example.leanwebtest.web.RobotsTxtTest
 * @see com.example.leanwebtest.web.StaticContentTest
 * @see com.example.leanwebtest.web.TomcatRemoteIpHeaderTest
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
    MvcConfig.class,
    WebSecurityConfig.class
})
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class,
    JpaRepositoriesAutoConfiguration.class
})
@Retention(RetentionPolicy.RUNTIME)
public @interface LeanWebTest {
}
