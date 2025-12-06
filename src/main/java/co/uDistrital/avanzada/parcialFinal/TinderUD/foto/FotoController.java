/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.foto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Maneja los endpoints relacionados a las fotos del usuario.
 *
 * @author Alex
 */
@RestController
public class FotoController {
    
    @Autowired 
    private FotoService service;
    
    /**
     * Crea una nueva foto.
     * Recibe un objeto Foto en formato JSON y retorna la foto creada.
     * 
     * @param foto Objeto Foto enviado en el cuerpo de la peticion
     * @return Foto creada dentro de un ResponseEntity
     */
    @RequestMapping(value = "/api/foto", method = RequestMethod.POST)
    @CrossOrigin(origins = "*") //Permite el acceso desde el frontend en este puerto
    public ResponseEntity<Foto> crearFoto(@RequestBody Foto foto) {
        return service.crearFoto(foto);
    }
    
    /**
     * Lista todas las fotos registradas.
     * 
     * @return Lista de fotos en formato JSON
     */
    @RequestMapping(value = "/api/foto", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Foto>> listarFotos() {
        List<Foto> lista = service.getAllFotos();
        return ResponseEntity.ok(lista);
    }
    
    /**
     * Obtiene una foto por su id.
     * 
     * @param id Identificador de la foto
     * @return Foto en formato JSON (puede ser null si no existe)
     */
    @RequestMapping(value = "/api/foto/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Foto> obtenerPorId(@PathVariable Long id) {
        Foto foto = service.findById(id);
        return ResponseEntity.ok(foto);
    }
    
    /**
     * Lista todas las fotos de un usuario.
     * 
     * @param idUsuario Id del usuario dueño de las fotos
     * @return Lista de fotos del usuario
     */
    @RequestMapping(value = "/api/foto/usuario/{idUsuario}",
            method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Foto>> listarPorUsuario(@PathVariable
            Long idUsuario) {
        List<Foto> lista = service.getFotosPorUsuario(idUsuario);
        return ResponseEntity.ok(lista);
    }
    
    /**
     * Elimina una foto por su id.
     * 
     * @param id Identificador de la foto
     * @return Respuesta vacia indicando que la operacion termino
     */
    @RequestMapping(value = "/api/foto/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Void> eliminarFoto(@PathVariable Long id) {
        service.eliminarFoto(id);
        return ResponseEntity.ok().build();
    }
    
}
