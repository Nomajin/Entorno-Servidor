package org.iesvdm.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.modelo.Cliente;


@Service
public class ClienteService {

    @Autowired 
    private ClienteDAO clienteDao;

    public List<Cliente> listAll() {
        return clienteDao.getAll();
    }
    
}
