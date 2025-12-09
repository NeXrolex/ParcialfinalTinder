/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.usuario;

import com.fasterxml.jackson.core.JsonParser;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Maneja los verbos y los endpoints relacionados a un usuario
 *
 * @author Alex
 */
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    
    /**
     * Crea un nuevo usuario
     * 
     * @param usuario Datos del usuario a crear
     * @return Usuario creado
     */
    @RequestMapping(value = "/api/usuario", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody
            Usuario usuario) {
        return service.crearUnUsuario(usuario);
    }
    
    /**
     * Obtiene todos los usuarios
     * 
     * @return Lista de todos los usuarios
     */
    @RequestMapping(value = "/api/usuario", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = service.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Obtiene un usuario por su ID
     * 
     * @param id Id del usuario
     * @return Usuario encontrado
     */
    @RequestMapping(value = "/api/usuario/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    /**
     * Obtiene un usuario por su correo
     * 
     * @param correo Correo del usuario
     * @return Usuario encontrado
     */
    @RequestMapping(value = "/api/usuario/correo/{correo}", method
            = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Usuario> obtenerPorCorreo(@PathVariable String correo) {
        Usuario usuario = service.findByCorreo(correo);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(usuario);
    }
    
    /**
     * actualiza un usuario existente
     * 
     * @param id Id del usuario a actualizar
     * @param usuario Datos actualizados
     * @return Usuario actualizado
     */
    @RequestMapping(value = "/api/usuario/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id,
            @RequestBody Usuario usuario) {
        Usuario actualizado = service.crearUsuario(usuario);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }
    
    /**
     * Elimina un usuario
     * 
     * @param id Id del usuario a eliminar
     * @return Respuesta de conformacion
     */
    @RequestMapping(value = "/api/usuario/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        service.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * Obtiene otros usuarios, excewptuando el logueado
     * 
     * @param id Id del usuario logueado
     * @return Lista de otros usuarios
     */
    // Este metodo devuelve todos los usuarios menos el actual (el que esta logueado)
    //Este metodo lo puse para mostrar los otros usuarios en las tarjetas para el match
    @RequestMapping(value = "/api/usuarios/otros/{id}", method = 
            RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Usuario>> obtenerOtrosUsuarios(@PathVariable
            Long id) {
        List<Usuario> todos = service.getAllUsuarios();
    
     // Filtrar el usuario actual
        List<Usuario> otros = todos.stream()
                               .filter(u -> !u.getId().equals(id))
                               .toList();
    
        return ResponseEntity.ok(otros);
    }
    
     /**
     * Envía un correo a un usuario
     * 
     * POST /api/usuario/{id}/enviar-correo
     * 
     * Body (JSON):
     * {
     *   "asunto": "Asunto del correo",
     *   "contenido": "Contenido del correo"
     * }
     */
    @PostMapping("/usuario/{id}/enviar-correo")
    public ResponseEntity enviarCorreoUsuario(@PathVariable Long id,
            @RequestBody Map<String, String> datos) {
        
        try {
            // Obtener asunto y contenido del Map
            String asunto = datos.get("asunto");
            String contenido = datos.get("contenido");
            
            // Validar que no sean nulos
            if (asunto == null || contenido == null) {
                return ResponseEntity.badRequest()
                    .body("Falta asunto o contenido en el JSON");
            }
            
            // Obtener usuario usando el método del service
            Usuario usuario = service.findById(id);
            
            // Validar que exista el usuario
            if (usuario == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Enviar correo
            service.enviarCorreoRegistro(usuario, asunto, contenido);
            
            return ResponseEntity.ok("Correo enviado exitosamente");
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error al enviar correo: " + e.getMessage());
        }
    }

}
