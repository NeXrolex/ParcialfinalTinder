/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.foto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.stereotype.Service;

/**
 * Manejo de los datos de la entidad Foto.
 *
 * @author Alex
 */
@Service
public class FotoService {

    @Autowired
    private FotoRepository repositorio;

    /**
     * Guarda la foto en la base de datos.
     *
     * @param foto Objeto Foto a guardar
     * @return Foto guardada
     */
    @CrossOrigin
    public Foto guardar(Foto foto) {
        return repositorio.save(foto);
    }

    /**
     * Obtiene todas las fotos por el id del usuario almacenadas en la base de
     * datos.
     *
     * @param idUsuario Id del usuario dueño de las fotos
     * @return Lista de las fotos por id del usuario
     */
    @CrossOrigin
    public List<Foto> getPorUsuario(Long idUsuario) {
        return repositorio.findByIdUsuarioOrderByOrdenAsc(idUsuario);
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
