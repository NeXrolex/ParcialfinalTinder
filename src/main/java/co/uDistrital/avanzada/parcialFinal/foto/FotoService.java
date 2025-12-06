/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.foto;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Manejo de los datos de la entidad Foto.
 *
 * @author Alex
 */
public class FotoService {
    
    @Autowired
    private FotoRepository repositorio;
    
    /**
     * Crea una nueva foto y la guarda en la base de datos.
     * Asigna la fecha y hora actuales si no vienen en el objeto.
     * 
     * @param foto Objeto Foto a guardar
     * @return Foto creada dentro de un ResponseEntity
     */
    @CrossOrigin
    public ResponseEntity<Foto> crearFoto(Foto foto) {
        //Si no viene la fecha, se asigna la fecha actual
        if (foto.getFechaPublicacion() == null) {
            foto.setFechaPublicacion(new Date());
        }
        Foto guardada = repositorio.save(foto);
        return ResponseEntity.ok(guardada);
    }
    
    /**
     * Obtiene todas las fotos almacenadas en la base de datos.
     * 
     * @return Lista de todas las fotos
     */
    @CrossOrigin
    public List<Foto> getAllFotos() {
        return repositorio.findAll();
    }
    
    /**
     * Busca una foto por su identificador.
     * 
     * @param id Identificador de la foto
     * @return Foto asociada al id o null si no existe
     */
    @CrossOrigin
    public Foto findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }
    
    /**
     * Obtiene todas las fotos de un usuario.
     * 
     * @param idUsuario Id del usuario dueño de las fotos
     * @return Lista de fotos del usuario
     */
    @CrossOrigin
    public List<Foto> getFotosPorUsuario(Long idUsuario) {
        return repositorio.findByIdUsuario(idUsuario);
    }
    
    /**
     * Elimina una foto por su identificador.
     * 
     * @param id Identificador de la foto a eliminar
     */
    @CrossOrigin
    public void eliminarFoto(Long id) {
        repositorio.deleteById(id);
    }
    
}
