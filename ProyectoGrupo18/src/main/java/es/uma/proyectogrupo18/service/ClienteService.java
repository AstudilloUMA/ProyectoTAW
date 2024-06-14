package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.dto.Cliente;
import es.uma.proyectogrupo18.dto.FeedbackDieta;
import es.uma.proyectogrupo18.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService extends DTOService<Cliente, ClienteEntity> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RutinaSemanalRepository rutinaSemanalRepository;

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackdietaRepository feedbackdietaRepository;

    @Autowired
    private SesionDeEjercicioRepository sesionDeEjercicioRepository;

    // Método para obtener todos los clientes
    public List<Cliente> getAllClientes() {
        List<ClienteEntity> clientes = clienteRepository.findAll();
        return this.entidadesADTO(clientes);
    }

    // Método para obtener un cliente por su ID
    public Cliente getClienteById(Integer id) {
        ClienteEntity cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            return cliente.toDTO();
        } else {
            return null;
        }
    }

    // Método para borrar un cliente
    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    // Método para guardar un cliente
    public void guardarCliente(Cliente cliente) {
        Integer id = cliente.getId();
        ClienteEntity clienteEntity = this.clienteRepository.findById(id).orElse(new ClienteEntity());
        clienteEntity.setId(id);
        clienteEntity.setUsuario(this.usuarioRepository.findById(cliente.getUsuario().getId()).orElse(null));
        clienteEntity.setPeso(cliente.getPeso());
        clienteEntity.setAltura(cliente.getAltura());
        clienteEntity.setEdad(cliente.getEdad());
        clienteEntity.setRutina(this.rutinaSemanalRepository.findById(cliente.getRutinaSemanal().getId()).orElse(null));
        clienteEntity.setDietaCodigo(this.dietaRepository.findById(cliente.getDieta().getId()).orElse(null));
        clienteEntity.setDietista(this.trabajadorRepository.findById(cliente.getDietista().getId()).orElse(null));
        clienteEntity.setEntrenador(this.trabajadorRepository.findById(cliente.getEntrenador().getId()).orElse(null));
        clienteEntity.setFeedbacks(this.feedbackRepository.findAllById(cliente.getFeedbacks()));
        clienteEntity.setFeedbackdietas(this.feedbackdietaRepository.findAllById(cliente.getFeedbackDietas()));
        clienteEntity.setSesionDeEjercicios(this.sesionDeEjercicioRepository.findAllById(cliente.getSesionDeEjercicios()));
        this.clienteRepository.save(clienteEntity);
    }
}
