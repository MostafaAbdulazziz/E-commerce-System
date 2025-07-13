package com.ecommerce.service;

import com.ecommerce.model.Shippable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    public void ship(List<Shippable> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        Map<String, Integer> itemCounts = new LinkedHashMap<>();

        for (Shippable item : items) {
            itemCounts.merge(item.getName(), 1, Integer::sum);
        }

        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            System.out.printf("%dx %s\n", entry.getValue(), entry.getKey());
        }

        for (Shippable item : items) {
            double weight = item.getWeight();
            totalWeight += weight;
            System.out.printf("%.0fg\n", weight * 1000);
        }

        System.out.println("Total package weight kg  "+totalWeight);
    }
}
