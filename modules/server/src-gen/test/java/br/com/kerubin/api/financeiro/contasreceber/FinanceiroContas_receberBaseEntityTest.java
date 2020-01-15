/**********************************************************************************************
Code generated with MKL Plug-in version: 23.0.0
Code generated at time stamp: 2019-09-20T20:28:06.684
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber;

import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.domain.AuditorAware;
import br.com.kerubin.api.database.entity.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;


@TestPropertySource(locations = "classpath:default-test.properties")
@DataJpaTest
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class FinanceiroContas_receberBaseEntityTest {
	
	
	@TestConfiguration
	static class FinanceiroContas_receberBaseEntityTestConfig {
		
		@Bean
		public AuditorAware<String> auditorAware() {
			return new AuditorAwareImpl();
		}
		
		@Bean
		public br.com.kerubin.api.servicecore.mapper.ObjectMapper objectMapper() {
			return new br.com.kerubin.api.servicecore.mapper.ObjectMapper();
		}
		
	}
	

}
