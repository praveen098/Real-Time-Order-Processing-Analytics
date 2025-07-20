package com.yourcompany.orderservice;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import com.yourcompany.order.OrderPlaced;
import com.yourcompany.order.Item;  // Avro-generated

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaTemplate<String, OrderPlaced> kafka;

    public OrderController(KafkaTemplate<String, OrderPlaced> kafka) {
        this.kafka = kafka;
    }

    @PostMapping
    public ResponseEntity<Void> placeOrder(@RequestBody OrderRequest req) {
        // map DTO items → Avro Items
        var avroItems = req.getItems().stream()
                .map(i -> Item.newBuilder()
                        .setItemId(i.getItemId())
                        .setQuantity(i.getQuantity())
                        .setPrice(i.getPrice())
                        .build())
                .collect(Collectors.toList());

        // build the Avro record
        var avroOrder = OrderPlaced.newBuilder()
                .setOrderId(req.getOrderId())
                .setCustomerId(req.getCustomerId())
                .setItems(avroItems)
                .setTotalAmount(req.getTotalAmount())
                .setOrderTimestamp(Instant.ofEpochMilli(req.getOrderTimestamp()))
                .build();

        kafka.send("order-placed", avroOrder.getOrderId().toString(), avroOrder);
        return ResponseEntity.accepted().build();
    }
}
