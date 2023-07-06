package com.example.demo.healthcareprovider;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class HealthcareProviderController {
    @Autowired
    private HealthcareProviderService providerService;

    @GetMapping
    public List<HealthcareProvider> getAllProviders() {
        return providerService.getAllProviders();
    }

    @GetMapping("/{id}")
    public HealthcareProvider getProviderById(@PathVariable Long id) {
        return providerService.getProviderById(id);
    }

    @PostMapping
    public HealthcareProvider createProvider(@RequestBody HealthcareProvider provider) {
        return providerService.createProvider(provider);
    }

    @PutMapping("/{id}")
    public HealthcareProvider updateProvider(@PathVariable Long id, @RequestBody HealthcareProvider provider) {
        return providerService.updateProvider(id, provider);
    }

    @DeleteMapping("/{id}")
    public void deleteProvider(@PathVariable Long id) {
        providerService.deleteProvider(id);
    }
}

