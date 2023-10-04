package com.elastic.service.data;

import com.elastic.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class DashBoardDataService {
    private static final List<String> productNames = List.of(
            "Smartphone", "Laptop", "Tablet", "Smartwatch", "Desktop Computer",
            "Digital Camera", "Headphones", "Television", "Gaming Console",
            "Bluetooth Speaker", "Fitness Tracker", "E-Reader", "Home Assistant",
            "Wireless Router", "Computer Monitor", "Printer", "Virtual Reality Headset",
            "Projector", "External Hard Drive", "Graphics Card");

    private static final List<String> productDescriptions = List.of(
            "A powerful and feature-rich smartphone.",
            "A high-performance laptop for productivity and gaming.",
            "A portable tablet for work and entertainment.",
            "A smartwatch with fitness tracking and notifications.",
            "A desktop computer for home or office use.",
            "A digital camera for capturing memories.",
            "High-quality headphones for immersive audio.",
            "A high-resolution television with smart features.",
            "A gaming console for gaming enthusiasts.",
            "A portable Bluetooth speaker for music on the go.",
            "A fitness tracker to monitor your health and activity.",
            "An e-reader for digital book lovers.",
            "A voice-controlled home assistant.",
            "A reliable wireless router for network connectivity.",
            "A computer monitor for crisp visuals.",
            "A versatile printer for documents and photos.",
            "An immersive virtual reality headset.",
            "A projector for home theater or presentations.",
            "An external hard drive for data backup.",
            "A graphics card for gaming and graphics-intensive tasks.");


    public List<Product> generateDashBoardData() {
        log.info("Let's Generate Data ------");
        List<Product> dashBoardData = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= 100000; i++) {
            Integer productId = i;
            String randomName = productNames.get(random.nextInt(productNames.size()));
            String productDescription = productDescriptions.get(random.nextInt(productDescriptions.size()));
            long quantity = random.nextInt();
            double price = 100.0 + (Math.random() * 1000);
            LocalDateTime purchasedAt = LocalDateTime.now();

            Product product = Product.builder()
                    .id(productId)
                    .name(randomName)
                    .description(productDescription)
                    .quantity((int) quantity)
                    .price(price)
                    .purchasedAt(purchasedAt)
                    .build();

            dashBoardData.add(product);
        }

        return dashBoardData;

    }

}