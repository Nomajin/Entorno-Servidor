package org.iesvdm.controlador;

import org.springframework.stereotype.Controller;
import org.iesvdm.servicios.ClienteService;
import org.springframework.ui.Model;
import java.util.List;
import org.iesvdm.modelo.Cliente;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ClienteControlador {

    private final ClienteService clienteService;

    public ClienteControlador(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public String listar(Model model) {

        List<Cliente> listaClientes = clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);

        return "clientes";
    }
    
}
