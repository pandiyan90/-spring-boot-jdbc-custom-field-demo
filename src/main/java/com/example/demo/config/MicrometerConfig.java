/*package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;

@Configuration
public class MicrometerConfig {

	@Bean
	MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer(MeterRegistry meterRegistry){
		return meterRegistry1 -> {
			meterRegistry.config()
 			.commonTags("customFieldDemoApplication", "micrometer-example");
		};
	}
	
}
*/