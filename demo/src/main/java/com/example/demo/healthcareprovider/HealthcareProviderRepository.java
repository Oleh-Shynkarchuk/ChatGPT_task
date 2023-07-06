package com.example.demo.healthcareprovider;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthcareProviderRepository extends JpaRepository<HealthcareProvider, Long> {
    // Additional custom query methods as needed
}
