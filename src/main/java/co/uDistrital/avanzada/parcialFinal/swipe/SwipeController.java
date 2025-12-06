/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.swipe;

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
 * Maneja los endpoint y las relaciones entre los
 * swipes
 *
 * @author Alex
 */
@RestController //Queremos datos json
public class SwipeController {
    
    @Autowired //Inyeccion de dependencias 
    private SwipeService service;
    
    /**
     * Crea un Swipe entre dos usuarios
     * 
     * @param swipe Swipe recibido desde el cliente
     * @return Respuesta con wl swipe creado 
     */
    //Se crea despues, por eso es post
    @RequestMapping(value = "/api/swipe", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:8383")
    //Lo requerimos serializado
    public ResponseEntity<Swipe> crearSwipe(@RequestBody Swipe swipe) {
        return service.crearSwipe(swipe);
    }
    
    /**
     * lista todos los swipe registrados 
     * 
     * Usa get porque es de consulta
     * @return Lista de todos los swipes
     */
    @RequestMapping(value = "/api/swipe", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<List<Swipe>> listarSwipes() {
        //Devuelve todos los registros
        return ResponseEntity.ok(service.getAllSwipes());
    }
    
    /**
     * Obtiene los swipe por un Id
     * 
     * @param id Identifficador
     * @return El swipe correspondiente
     */
    @RequestMapping(value = "/api/swipe/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Swipe> obtenerPorId(@PathVariable Long id) {
        Swipe swipe = service.findById(id);
        //Lo regresa serializado
        return ResponseEntity.ok(swipe);
    }
    
    /**
     * Lista todos los swipe enviados por
     * usuario en especifico
     * 
     * @param idEmisor
     * @return 
     */
    @RequestMapping(value = "/api/swipe/emisor/{idEmisor}",
            method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<List<Swipe>> listarPorEmisor(@PathVariable
            Long idEmisor) {
        //Devuelve los swipe filtados por el id del emisor
        return ResponseEntity.ok(service.getSwipesPorEmisor(idEmisor));
    }
    
    /**
     * Lista todos los swipe recibidos por un usuario
     * en especifico
     * 
     * @param idReceptor Identificador del receptor
     * @return Lista de swipes
     */
    @RequestMapping(value = "/api/swipe/receptor/{idReceptor}",
            method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<List<Swipe>> listarPorReceptor(@PathVariable 
            Long idReceptor) {
        return ResponseEntity.ok(service.getSwipesPorReceptor(idReceptor));
    }
    
    /**
     * Elimina un swipe existente por un id
     * 
     * @param id Id a eliminar
     * @return Respuesta segun el exito de la acciopn
     */
    @RequestMapping(value = "/api/swipe/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Void> eliminarSwipe(@PathVariable Long id) {
        service.eliminarSwipe(id);
        return ResponseEntity.ok().build();
    }
}
