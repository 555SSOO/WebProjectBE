package rest;

import org.glassfish.jersey.server.ResourceConfig;
import rest.filters.CorsFilter;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest")
public class RestApp extends ResourceConfig {

    public RestApp() {
        packages("rest");
        register(CorsFilter.class);
    }
}
