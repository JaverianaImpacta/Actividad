package edu.javeriana.ingenieria.social.actividad.controlador;

import edu.javeriana.ingenieria.social.actividad.dominio.Actividad;
import edu.javeriana.ingenieria.social.actividad.servicio.ServicioActividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/actividades")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorActividad {
    @Autowired
    private ServicioActividad servicio;

    @GetMapping("listar")
    public List<Actividad> obtenerActividades() {
        return servicio.obtenerActividades();
    }

    @GetMapping("obtener")
    public ResponseEntity<Actividad> obtenerActividad(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerActividad(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerActividad(id), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Actividad> crearActividad(@RequestBody Actividad actividad) {
        if(actividad == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actividadExiste(actividad.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearActividad(actividad), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Actividad> actualizarActividad(@RequestParam Integer id, @RequestBody Actividad actividad) {

        if(!Objects.equals(id, actividad.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(servicio.actualizarActividad(id, actividad) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actividad, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> borrarActividad(@RequestParam Integer id) {
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarActividad(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
