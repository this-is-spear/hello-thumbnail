package tis;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class ThumbnailFilter  extends AbstractGatewayFilterFactory<ThumbnailFilter.Config> {

    private final Logger log = LoggerFactory.getLogger(GlobalDefaultFilter.class);

    public ThumbnailFilter() {
        super(ThumbnailFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            log.info("execute thumbnail {}", config.getMessage());

            return chain.filter(exchange);
        };
    }

    public static class Config {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
