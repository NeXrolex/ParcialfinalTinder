/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.seguidor;

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
 * Maneja end points y relaciones entre los seguidores
 *
 *
 * @author Alex
 */
@RestController
public class SeguidorController {

    @Autowired
    private SeguidorService service;

    /**
     * Crea una nueva relacion de seguimiento
     *
     * @param seguidor Objeto con idSeguidor e idSeguido
     * @return Relacion creada
     */
    @RequestMapping(value = "/api/seguidor", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Seguidor> 
        crearSeguidor(@RequestBody Seguidor seguidor) {
        return service.crearSeguidor(seguidor);
    }

    /**
     * Lista todas las relaciones de seguidores
     */
    @RequestMapping(value = "/api/seguidor", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<List<Seguidor>> listarSeguidores() {
        List<Seguidor> lista = service.getAllSeguidores();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    /**
     * Lista las personas a las que sigue un usuario
     * 
     * @param idSeguidor Id del usuario seguidor
     */
    @RequestMapping(value="/api/seguidor/seguidos/{idSeguidor}",
            method = RequestMethod.GET)
    @CrossOrigin(origins="http://localhost:8383")
    public ResponseEntity<List<Seguidor>> listarSeguidosPor(@PathVariable
            Long idSeguidor) {
        List<Seguidor> lista = service.getSeguidosPor(idSeguidor);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    /**
     * Lista las personas que siguen a un usuario
     * 
     * @param idSeguido Id del usuario seguido
     */
    @RequestMapping(value="/api/seguidor/seguidores/{idSeguido}",
            method = RequestMethod.GET)
    @CrossOrigin(origins="http://localhost:8383")
    public ResponseEntity<List<Seguidor>> listarSeguidoresDe(@PathVariable
            Long idSeguido) {
        List<Seguidor> lista = service.getSeguidoresDe(idSeguido);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    /**
     * Elimina una relacion de seguimiento por id
     * 
     * @param id Identificador de la relacion
     */
    @RequestMapping(value="/api/seguidor/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins="http://localhost:8383")
    public ResponseEntity<Void> eliminarSeguidor(@PathVariable Long id) {
        service.eliminarSeguidor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
