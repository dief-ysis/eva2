package ventas.usuario.controller;

import ventas.usuario.model.Usuario;
import ventas.usuario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marca esta clase como un controlador REST
@RequestMapping("/api/v1/usuarios") // Define la ruta base para todos los endpoints de este controlador
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired // Inyecta una instancia de UsuarioService
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint para crear un nuevo usuario (POST /api/v1/usuarios)
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED); // Devuelve 201 Created
    }

    // Endpoint para obtener un usuario por ID (GET /api/v1/usuarios/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id)
               .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK)) // Devuelve 200 OK
               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Devuelve 404 Not Found
    }

    // Endpoint para obtener todos los usuarios (GET /api/v1/usuarios)
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK); // Devuelve 200 OK
    }

    // Endpoint para actualizar un usuario existente (PUT /api/v1/usuarios/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalles) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDetalles);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK); // Devuelve 200 OK
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 Not Found si el usuario no existe
        }
    }

    // Endpoint para eliminar un usuario (DELETE /api/v1/usuarios/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devuelve 204 No Content
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 Not Found si el usuario no existe
        }
    }
}