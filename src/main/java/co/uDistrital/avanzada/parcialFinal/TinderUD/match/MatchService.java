/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.match;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Manejo de los datos de la entidad Match.
 *
 * @author Alex
 */
public class MatchService {
    
    @Autowired //Permite que Spring cree e inyecte automaticamente el repositorio
    private MatchRepository repositorio;
    
    /**
     * Crea un nuevo match y lo guarda en la base de datos.
     * 
     * @param match Objeto Match a guardar
     * @return Match creado dentro de un ResponseEntity
     */
    @CrossOrigin
    public ResponseEntity<Match> crearMatch(Match match) {
        //Si no viene la fecha, se asigna la fecha actual
        if (match.getFechaHora() == null) {
            match.setFechaHora(new Date());
        }
        Match guardado = repositorio.save(match);
        return ResponseEntity.ok(guardado);
    }
    
    /**
     * Obtiene todos los matches almacenados en la base de datos.
     * 
     * @return Lista de todos los matches
     */
    @CrossOrigin
    public List<Match> getAllMatches() {
        return repositorio.findAll();
    }
    
    /**
     * Busca un match por su identificador.
     * 
     * @param id Identificador del match
     * @return Match asociado al id o null si no existe
     */
    @CrossOrigin
    public Match findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }
    
    /**
     * Obtiene todos los matches asociados a un usuario,
     * 
     * @param idUsuario Id del usuario
     * @return Lista de matches donde participa el usuario
     */
    @CrossOrigin
    public List<Match> getMatchesPorUsuario(Long idUsuario) {
        return repositorio.findByIdUsuario1OrIdUsuario2(idUsuario, idUsuario);
    }
    
    /**
     * Elimina un match por su identificador.
     * 
     * @param id Identificador del match a eliminar
     */
    @CrossOrigin
    public void eliminarMatch(Long id) {
        repositorio.deleteById(id);
    }
    
    /**
     * Crea un match a partir de dos usuarios.
     * 
     * @param idUsuario1 Id del primer usuario
     * @param idUsuario2 Id del segundo usuario
     * @return Match creado
     */
    @CrossOrigin
    public Match crearMatchEntreUsuarios(Long idUsuario1, Long idUsuario2) {
        Match match = new Match(idUsuario1, idUsuario2, new Date(), "ACTIVO");
        return repositorio.save(match);
    }
    
}
