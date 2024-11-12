
package com.example.personas.repositories;
//Repositorio utilizando el PersonasRepository y se van a poder ejecutar las acciones para buscar, guardar, eliminar y demas sobre las personas de la tabla
// En pocas palabras el repositorio permite ejecutar las acciones mencionadas
//importamos el JPA y la pk de la tabla 

import com.example.personas.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonasRepository extends JpaRepository<Persona,Long> {
    
}
