package com.example.order_service.springcloud;

import com.example.common.dto.order.OrderRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class SpringCloudStreamPubliser {

    private static final Logger log = LoggerFactory.getLogger(SpringCloudStreamPubliser.class);

    private final StreamBridge streamBridge;

    public SpringCloudStreamPubliser(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void send(OrderRequestDTO orderRequestDTO) {
        streamBridge.send("order-out-0", orderRequestDTO);
        log.info("{} sent!", orderRequestDTO.toString());
    }
}
