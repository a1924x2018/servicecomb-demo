package demo;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestSchema(schemaId = "providerController")
@RequestMapping(path = "/")
public class ProviderController {

    @GetMapping("/provider")
    public String provider() {
        return "provider";
    }

}
