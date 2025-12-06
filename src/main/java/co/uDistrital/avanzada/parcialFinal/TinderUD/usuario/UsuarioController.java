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
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @RequestMapping(value = "/api/usuario", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return service.crearUnUsuario(usuario);
    }

    @RequestMapping(value = "/api/usuario", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = service.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @RequestMapping(value = "/api/usuario/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @RequestMapping(value = "/api/usuario/correo/{correo}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Usuario> obtenerPorCorreo(@PathVariable String correo) {
        Usuario usuario = service.findByCorreo(correo);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(usuario);
    }

    @RequestMapping(value = "/api/usuario/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id,
            @RequestBody Usuario usuario) {
        Usuario actualizado = service.crearUsuario(usuario);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/usuario/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        service.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
