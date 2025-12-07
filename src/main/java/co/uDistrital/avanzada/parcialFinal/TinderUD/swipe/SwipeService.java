/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.swipe;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Maneja los datos del
 * swipe entre usuarios
 *
 * @author Alex
 */
@Service 
public class SwipeService {
    
    @Autowired
    private SwipeRepository repositorio;
    
     /**
     * Crea un nuevo swipe
     * 
     * @param swipe Objeto swipe
     * @return Swipe creado
     */
    @CrossOrigin
    public ResponseEntity<Swipe> crearSwipe(Swipe swipe) {
        //Asignar fecha actual
        swipe.setFechaHora(new Date());
        Swipe guardado = repositorio.save(swipe);
        return ResponseEntity.ok(guardado);
    }
    
    /**
     * Obtiene todos los swipes
     * 
     * @return Lista de swipes
     */
    @CrossOrigin
    public List<Swipe> getAllSwipes() {
        return repositorio.findAll();
    }
    
    /**
     * Busca un swipe por id
     * 
     * @param id Identificador del swipe
     * @return Swipe o null si no existe
     */
    @CrossOrigin
    public Swipe findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }
    
    /**
     * Swipes hechos por un usuario (emisor)
     */
    @CrossOrigin
    public List<Swipe> getSwipesPorEmisor(Long idEmisor) {
        return repositorio.findByIdEmisor(idEmisor);
    }
    
    /**
     * Swipes recibidos por un usuario (receptor)
     */
    @CrossOrigin
    public List<Swipe> getSwipesPorReceptor(Long idReceptor) {
        return repositorio.findByIdReceptor(idReceptor);
    }
    
    /**
     * Elimina un swipe por id
     */
    @CrossOrigin
    public void eliminarSwipe(Long id) {
        repositorio.deleteById(id);
    }
    
}
