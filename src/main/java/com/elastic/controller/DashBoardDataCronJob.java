package com.elastic.controller;

import com.elastic.model.Product;
import com.elastic.repository.ProductRepository;
import com.elastic.service.data.DashBoardDataService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DashBoardDataCronJob {

    private final ProductRepository productRepository;

    private final DashBoardDataService dashBoardDataService;

    @Scheduled(cron = "0 */1 * * * *")
    public void runCronJob() {
        List<Product> dashBoardData = dashBoardDataService.generateDashBoardData();
        int batchSize = 10000;

        for (int i = 0; i < dashBoardData.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, dashBoardData.size());
            List<Product> batchToSave = dashBoardData.subList(i, endIndex);
            productRepository.saveAll(batchToSave);
        }
    }

}
