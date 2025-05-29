package ventas.usuario.services;

import ventas.usuario.model.Usuario;
import ventas.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired 
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para crear un nuevo usuario
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método para obtener un usuario por su ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para actualizar un usuario existente
    public Usuario actualizarUsuario(Long id, Usuario usuarioDetalles) {
        return usuarioRepository.findById(id)
               .map(usuarioExistente -> {
                    usuarioExistente.setNombre(usuarioDetalles.getNombre());
                    usuarioExistente.setEmail(usuarioDetalles.getEmail());
                    // Solo actualiza la contraseña si se proporciona una nueva y es diferente
                    if (usuarioDetalles.getContrasena()!= null &&!usuarioDetalles.getContrasena().isEmpty()) {
                        usuarioExistente.setContrasena(usuarioDetalles.getContrasena());
                    }
                    usuarioExistente.setRol(usuarioDetalles.getRol());
                    return usuarioRepository.save(usuarioExistente);
                })
               .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    // Método para eliminar un usuario por su ID
    public void eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}