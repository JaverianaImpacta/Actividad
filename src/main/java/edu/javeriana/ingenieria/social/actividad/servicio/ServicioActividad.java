package edu.javeriana.ingenieria.social.actividad.servicio;

import edu.javeriana.ingenieria.social.actividad.dominio.Actividad;
import edu.javeriana.ingenieria.social.actividad.repositorio.RepositorioActividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioActividad {
    @Autowired
    private RepositorioActividad repositorio;

    public List<Actividad> obtenerActividades(){
        return repositorio.findAll();
    }

    public Actividad obtenerActividad(Integer id){
        return repositorio.findById(id).orElse(null);
    }

    public Actividad crearActividad(Actividad actividad) {
        return repositorio.save(actividad);
    }

    public Actividad actualizarActividad(Integer id, Actividad actividad) {
        if(repositorio.findById(id).orElse(null) == null) {
            return null;
        }
        actividad.setId(id);
        return repositorio.save(actividad);
    }

    public Actividad borrarActividad(Integer id) {
        Actividad aux = repositorio.findById(id).orElse(null);
        if(aux == null) {
            return aux;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean actividadExiste(Integer id) {
        return repositorio.existsById(id);
    }
}
