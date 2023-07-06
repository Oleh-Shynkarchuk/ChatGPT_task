package com.example.demo.healthcareprovider;

import java.util.List;

import com.example.demo.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthcareProviderService {
    @Autowired
    private HealthcareProviderRepository providerRepository;

    public List<HealthcareProvider> getAllProviders() {
        return providerRepository.findAll();
    }

    public HealthcareProvider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found with id: " + id));
    }

    public HealthcareProvider createProvider(HealthcareProvider provider) {
        return providerRepository.save(provider);
    }

    public HealthcareProvider updateProvider(Long id, HealthcareProvider providerDetails) {
        HealthcareProvider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found with id: " + id));

        provider.setName(providerDetails.getName());
        provider.setSpecialization(providerDetails.getSpecialization());
        // Update other provider attributes as needed

        return providerRepository.save(provider);
    }

    public void deleteProvider(Long id) {
        HealthcareProvider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found with id: " + id));

        providerRepository.delete(provider);
    }
}

