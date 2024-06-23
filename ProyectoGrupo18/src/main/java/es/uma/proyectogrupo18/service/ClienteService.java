/* Autores: Álvaro Morales Perujo: 60%
Juanma Porcuna Martín: 30%
Miguel Sánchez Hontoria: 6%
Andrés Santaella González: 4%

 */
package es.uma.proyectogrupo18.service;

import es.uma.proyectogrupo18.dao.*;
import es.uma.proyectogrupo18.dto.Cliente;
import es.uma.proyectogrupo18.dto.FeedbackDieta;
import es.uma.proyectogrupo18.entity.ClienteEntity;
import es.uma.proyectogrupo18.entity.UsuarioEntity;
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

    public List<Cliente> getClienteByTrainerId(Integer trabajador) {
        List<ClienteEntity> clientes = clienteRepository.findClientesByEntrenador(trabajador);
        return this.entidadesADTO(clientes);
    }

    public List<Cliente> getClientesByDietistaId(Integer trabajador) {
        List<ClienteEntity> clientes = clienteRepository.findClientesByDietista(trabajador);
        return this.entidadesADTO(clientes);
    }

    // Método para borrar un cliente
    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public void actualizarCliente(){
        this.clienteRepository.flush();
    }

    // Método para guardar un cliente
    public Integer guardarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = this.clienteRepository.findById(cliente.getId()).orElse(new ClienteEntity());

        if (clienteEntity.getId() == null) {
            clienteEntity.setId(cliente.getId());
        }

        clienteEntity.setUsuario(this.usuarioRepository.findById(cliente.getUsuario().getId()).orElse(null));
        clienteEntity.setPeso(cliente.getPeso());
        clienteEntity.setAltura(cliente.getAltura());
        clienteEntity.setEdad(cliente.getEdad());
        clienteEntity.setRutina(cliente.getRutinaSemanal() != null ? this.rutinaSemanalRepository.findById(cliente.getRutinaSemanal().getId()).orElse(null) : null);
        clienteEntity.setDietaCodigo(cliente.getDieta() != null ? this.dietaRepository.findById(cliente.getDieta().getId()).orElse(null) : null);
        clienteEntity.setDietista(cliente.getDietista() != null ? this.trabajadorRepository.findById(cliente.getDietista().getId()).orElse(null) : null);
        clienteEntity.setEntrenador(cliente.getEntrenador() != null ? this.trabajadorRepository.findById(cliente.getEntrenador().getId()).orElse(null) : null);
        clienteEntity.setFeedbacks(cliente.getFeedbacks() != null ? this.feedbackRepository.findAllById(cliente.getFeedbacks()) : null);
        clienteEntity.setFeedbackdietas(cliente.getFeedbackDietas() != null ? this.feedbackdietaRepository.findAllById(cliente.getFeedbackDietas()) : null);
        clienteEntity.setSesionDeEjercicios(cliente.getSesionDeEjercicios() != null ? this.sesionDeEjercicioRepository.findAllById(cliente.getSesionDeEjercicios()) : null);

        this.clienteRepository.save(clienteEntity);
        cliente.setId(clienteEntity.getId());

        return clienteEntity.getId();
    }

    public void crearCliente(Cliente cliente) {
        if (cliente.getId() == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }

        UsuarioEntity usuarioEntity = usuarioRepository.findById(cliente.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(cliente.getId());
        clienteEntity.setUsuario(usuarioEntity);

        usuarioEntity.setCliente(clienteEntity);

        usuarioRepository.save(usuarioEntity);
    }
}
