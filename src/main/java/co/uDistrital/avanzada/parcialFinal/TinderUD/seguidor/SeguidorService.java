/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.seguidor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Manejo de dataos del seguidor
 *
 * @author Alex
 */
@Service
public class SeguidorService {
    
    @Autowired
    private SeguidorRepository repositorio;
    
    /**
     * Crea una nueva relacion de seguimiento
     * 
     * @param seguidor Objeto seguidor con los ids de los usuarios
     * @return Relacion creada
     */
    @CrossOrigin
    public ResponseEntity<Seguidor> crearSeguidor(Seguidor seguidor) {
        Seguidor guardado = repositorio.save(seguidor);
        return ResponseEntity.ok(guardado);
    }
    /**
     * Obtiene todas las relaciones de seguidores
     * 
     * @return Lista de relaciones
     */
    @CrossOrigin
    public List<Seguidor> getAllSeguidores() {
        return repositorio.findAll();
    }
    
    /**
     * Obtiene las personas a las que sigue un usuario
     * 
     * @param idSeguidor Id del usuario seguidor
     * @return Lista de relaciones donde idSeguidor es el seguidor
     */
    @CrossOrigin
    public List<Seguidor> getSeguidosPor(Long idSeguidor) {
        return repositorio.findByIdSeguidor(idSeguidor);
    }
    
    /**
     * Obtiene las personas que siguen a un usuario
     * 
     * @param idSeguido Id del usuario seguido
     * @return Lista de relaciones donde idSeguido es el seguido
     */
    @CrossOrigin
    public List<Seguidor> getSeguidoresDe(Long idSeguido) {
        return repositorio.findByIdSeguido(idSeguido);
    }
    
    /**
     * Elimina una relacion de seguimiento por id
     * 
     * @param id Identificador de la relacion
     */
    @CrossOrigin
    public void eliminarSeguidor(Long id) {
        repositorio.deleteById(id);
    }
    
}
