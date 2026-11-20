package org.utn.facturacion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.utn.facturacion.service.FacturaService;

import java.util.Date;

@SpringBootApplication
public class FacturacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacturacionApplication.class, args);


	}
	@Bean
	CommandLineRunner test(FacturaService facturaService){
		return args -> {

			facturaService.obtenerReporteFacturas()
					.forEach(System.out::println);


			facturaService.obtenerReporteFacturas(new Date(), null, null, null)
					.forEach(System.out::println);

		};
	}
}


