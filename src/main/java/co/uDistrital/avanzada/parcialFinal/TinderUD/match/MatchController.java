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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Maneja los endpoints relacionados a los matches.
 *
 * @author Alex
 */
@RestController
public class MatchController {

    @Autowired //Inyecta el servicio para poder usarlo en los metodos del controlador
    private MatchService service;

    /**
     * Crea un nuevo match. Recibe un objeto Match en formato JSON y retorna el
     * match creado.
     *
     * @param match Objeto Match enviado en el cuerpo de la peticion
     * @return Match creado dentro de un ResponseEntity
     */
    @RequestMapping(value = "/api/match", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:8383") //Permite el acceso desde el frontend en este puerto
    public ResponseEntity<Match> crearMatch(@RequestBody Match match) {

        if (match.getFechaHora() == null) {
            match.setFechaHora(new Date());
        }

        if (match.getEstado() == null || match.getEstado().isEmpty()) {
            match.setEstado("ACTIVO");  // Valor por defecto
        }

        return service.crearMatch(match);
    }

    /**
     * Lista todos los matches registrados.
     *
     * @return Lista de matches en formato JSON
     */
    @RequestMapping(value = "/api/match", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<List<Match>> listarMatches() {
        List<Match> lista = service.getAllMatches();
        return ResponseEntity.ok(lista);
    }

    /**
     * Obtiene un match por su id.
     *
     * @param id Identificador del match
     * @return Match en formato JSON (puede ser null si no existe)
     */
    @RequestMapping(value = "/api/match/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Match> obtenerPorId(@PathVariable Long id) {
        Match match = service.findById(id);
        return ResponseEntity.ok(match);
    }

    /**
     * Lista todos los matches asociados a un usuario.
     *
     * @param idUsuario Id del usuario
     * @return Lista de matches donde participa el usuario
     */
    @RequestMapping(value = "/api/match/usuario/{idUsuario}",
            method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<List<Match>> listarPorUsuario(@PathVariable Long idUsuario) {
        List<Match> lista = service.getMatchesPorUsuario(idUsuario);
        return ResponseEntity.ok(lista);
    }

    /**
     * Elimina un match por su id.
     *
     * @param id Identificador del match
     * @return Respuesta vacia indicando que la operacion termino
     */
    @RequestMapping(value = "/api/match/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:8383")
    public ResponseEntity<Void> eliminarMatch(@PathVariable Long id) {
        service.eliminarMatch(id);
        return ResponseEntity.ok().build();
    }

}
