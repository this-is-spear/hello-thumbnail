package tis

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component

@Component
class DefaultFilter : AbstractGatewayFilterFactory<DefaultFilterConfig>(DefaultFilterConfig::class.java) {
    private val log = LoggerFactory.getLogger(this.javaClass)!!

    override fun apply(config: DefaultFilterConfig): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            log.info(config.defaultMessage)

            if (config.preLogger) {
                log.info("log start ${exchange.request}")
            }

            chain.filter(exchange).doFinally {
                if (config.postLogger) {
                    log.info("log end ${exchange.request}")
                }
            }
        }
    }
}

class DefaultFilterConfig(val defaultMessage: String,
                          val preLogger: Boolean,
                          val postLogger: Boolean)
