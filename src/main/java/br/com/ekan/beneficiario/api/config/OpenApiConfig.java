package br.com.ekan.beneficiario.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class OpenApiConfig {

	@Value("${api.info.title}")
	public String title;

	@Value("${api.info.description}")
	public String description;

	@Value("${api.info.version}")
	public String version;

	@Value("${api.info.termsOfServiceUrl}")
	public String termsOfServiceUrl;

	@Value("${api.info.contact.name}")
	public String contactName;

	@Value("${api.info.contact.url}")
	public String contactUrl;

	@Value("${api.info.contact.email}")
	public String contactEmail;

	@Value("${api.info.license}")
	public String license;

	@Value("${api.info.licenseUrl}")
	public String licenseUrl;

    @Bean
    OpenAPI customOpenAPI() {
//    	final String securitySchemeName = "bearerAuth";

		return new OpenAPI()
//				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//		        	.components(new Components()
//		                .addSecuritySchemes(
//		                		securitySchemeName,
//		                		new SecurityScheme()
//		                        	.name(securitySchemeName)
//		                        	.type(SecurityScheme.Type.HTTP)
//		                        	.scheme("bearer")
//		                        	.bearerFormat("JWT")))
				.info(new Info()
				.title(title)
				.description(description)
				.version(version)
				.termsOfService(termsOfServiceUrl)
				.contact(new Contact()
						.name(contactName)
						.url(contactUrl)
						.email(contactEmail))
				.license(new License()
						.name(license)
						.url(licenseUrl))
				);
				
	}
    
    // Grupo para a versão completa da API
//    @Bean
//    GroupedOpenApi apiGroupFull() {
//        return GroupedOpenApi.builder()
//                .group("01 - Versão Completa")
//                .packagesToScan("br.com.grupogrowth7.calendar.api.resources.controllers")
//                .build();
//    }

    // Grupo para um subconjunto específico da API (exemplo: Poder de Mãe)
//    @Bean
//    GroupedOpenApi apiGroupSubset() {
//        return GroupedOpenApi.builder()
//                .group("02 - Poder de Mãe")
//                .pathsToMatch(
//                		"/internals/program-users/**", 
//                		"/calendars/**", 
//                		"/events/**")
//                .pathsToExclude(
//                		"/calendars/integrations/**", 
//                		"/calendars/sincronizations/**", 
//                		"/calendars/streamings/**", 
//                		"/events/integrations/**", 
//                		"/events/sincronizations/**", 
//                		"/events/streamings/**")
//                .build();
//    }

}