package unicorn.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.apache.log4j.Logger;
import unicorn.bean.DisplayBeanImpl;
import unicorn.dto.EventDTO;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Singleton
@ApplicationScoped
public class WebServiceImpl implements WebService {
    private static final Logger logger = Logger.getLogger(DisplayBeanImpl.class);
    private final String GET_ALL_EVENTS_URL = "http://localhost:8080/unicorn/rest/events-today";
    private final int STATUS_OK = 200;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<EventDTO> getEvents() {
        WebResource webResource = getWebResource();
        ClientResponse response = queryGet(webResource);
        List<EventDTO> entityList = new ArrayList<>();
        try {
            if (response.getStatus() == STATUS_OK) {
                entityList = mapper.readValue(response.getEntity(String.class), new TypeReference<List<EventDTO>>() {});
            }
        } catch (Exception ex) {
            logger.error("Exception: Can't convert response to list of events");
        }
        return entityList;
    }

    private WebResource getWebResource() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        return client.resource(GET_ALL_EVENTS_URL);

    }

    protected ClientResponse queryGet(WebResource webResource) {
        return webResource.type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
    }
}
