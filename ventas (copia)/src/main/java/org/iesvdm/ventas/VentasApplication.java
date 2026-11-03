package org.iesvdm.ventas;

import java.util.Optional;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication(scanBasePackages = "org.iesvdm")
@RestController 
@Slf4j 
@SuppressWarnings("SprinJavaAutowire")
public class VentasApplication implements CommandLineRunner {

	@SuppressWarnings ({"java:S6813", })
	private ClienteDAO clienteDao;
	private ComercialDAO comercialDao;

	VentasApplication(ClienteDAO clienteDao, ComercialDAO comercialDao) {
		this.clienteDao = clienteDao;
		this.comercialDao = comercialDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(VentasApplication.class, args);

	}

	@GetMapping("/")
	public String home() {
		return ("Welcome to Spring Boot JDBC with H2 Database!");
	}

	@Override 

	public void run(String... args) throws Exception {
		log.info("Aplicación iniciada correctamente.");
		log.info("Ejecutando código de prueba de la capa de persistencia (DAO).");

		//Prueba de la capa DAO
		//Se puede probar con un id que exista o no en la base de datos.

		clienteDao.getAll().forEach(c -> log.info("Cliente: " + c));

		int id = 1;
		Optional<Cliente> cliente = clienteDao.find(id);

		if (cliente.isPresent()) {
			log.info("Cliente encontrado: " + cliente.get());

			String nombre = cliente.get().getNombre();
			clienteDao.update(cliente.get());
			log.info("Cliente actualizado: " + cliente.get());

			clienteDao.getAll().forEach(c -> log.info("Cliente: " + c));

			cliente.get().setNombre(nombre);
			clienteDao.update(cliente.get());
			log.info("Cliente restaurado: " + cliente.get());
		} else {
			log.info("Cliente con id " + id + " no encontrado.");
		}

		log.info("Ejecutando pruebas de la capa DAO de Comercial.");
		comercialDao.getAll().forEach(c -> log.info("Comercial: " + c));

		int comercialId = 1;
		Optional<Comercial> comercial = comercialDao.find(comercialId);
		if (comercial.isPresent()) {
			String nombre = comercial.get().getNombre();
			comercial.get().setNombre(nombre + " (prueba)");
			comercialDao.update(comercial.get());
			log.info("Comercial actualizado: {}", comercialDao.find(comercialId).orElseThrow());

			comercial.get().setNombre(nombre);
			comercialDao.update(comercial.get());
			log.info("Comercial restaurado: {}", comercial.get());
		} else {
			log.info("Comercial con id {} no encontrado.", comercialId);
		}

		Comercial nuevoComercial = new Comercial(0, "Prueba", "Temporal", null, 0.10f);
		comercialDao.create(nuevoComercial);
		log.info("Comercial creado: {}", comercialDao.find(nuevoComercial.getId()).orElseThrow());
		comercialDao.delete(nuevoComercial.getId());

	}
	

}
