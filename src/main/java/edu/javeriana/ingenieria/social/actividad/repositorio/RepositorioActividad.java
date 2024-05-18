package edu.javeriana.ingenieria.social.actividad.repositorio;

import edu.javeriana.ingenieria.social.actividad.dominio.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioActividad extends JpaRepository<Actividad, Integer> {
}
