/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.usuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Maneja los verbos y los endpoints relacionados a un usuario
 *
 * @author Alex
 */
@RestController//Vamos atrabajar con json para que los pueda 
//recibir el javaScrip
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @RequestMapping(value = "/api/usuario", method = RequestMethod.POST)
    //El metodo es post ya que se recibe despues del html
    @CrossOrigin(origins = "http://localhost:8383")
    //Necesitamos retornar serializado y tambien recibir serializado
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {

        return service.crearUnUsuario(usuario);

    }

    /**
     * Obtiene todos los usuarios
     *
     * @return Lista de usuarios
     */
    //Usamos Get porque es una consulta
    @RequestMapping(value = "/api/usuario", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = service.getAllUsuarios();
        return (ResponseEntity<List<Usuario>>) usuarios;
    }

    /**
     * Obtiene un usuario por su id
     *
     * @param id Id
     * @return Usuario Usuario serializado
     */
    @RequestMapping(value = "/api/usuario/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * Obtiene un usuario por su correo
     *
     * @param correo Correo del usuario
     * @return Usuario asociado a ese correo de manera serializada
     */
    @RequestMapping(value = "/api/usuario/{correo}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Usuario> obtenerPorCorreo(@PathVariable String correo) {
        Usuario usuario = service.findByCorreo(correo);
        //Devolvemos la respuesta HTTP con el usuario en formato JSON
        return ResponseEntity.ok(usuario);
    }

    /**
     * Actualiza un usari
     *
     * @param id ID del usuaio
     * @param usuario Usuario
     * @return Estado de la actualizacion
     */
    @RequestMapping(value = "/api/usuario/{id]", method = RequestMethod.PUT)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id,
            @RequestBody Usuario usuario) {
        Usuario actualizado = service.crearUsuario(usuario);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }
    
    /**
     * Realiza un login de inicio de sesion
     * 
     * @param credenciales Credenciales de inicio 
     * @return Usuario serealizado
     */
    @RequestMapping(value = "/api/usuario/login", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Usuario> login(@RequestBody Usuario credenciales) {
        Usuario usuario = service.login(credenciales.getCorreo(),
                credenciales.getPassword());
        return ResponseEntity.ok(usuario); // puede devolver null si no existe
    }

    /**
     * Elimina a un usario
     *
     * @param id ID el usario
     * @return Estado de la eliminacion
     */
    @RequestMapping(value = "/api/usuario/{id]", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        service.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
