
package com.example.personas.controllers;

import com.example.personas.models.Persona;
import com.example.personas.repositories.PersonasRepository;
import java.util.List;
import java.util.Optional;
import static org.hibernate.query.results.Builders.entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.status;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Indica que esta clase va a ser el controlador
@RestController
//Anotaciones que nos dice que tipo de ruta controla este controlador
@RequestMapping("/api/personas")
public class PersonasController {
    
    @Autowired
    private PersonasRepository personaRepository;
    
    //mapear todas las peticiones que lleguen por el verbo get 
    @GetMapping
    public List <Persona> getAllPersona(){
        return personaRepository.findAll();
    }
    @CrossOrigin
    //No pueden haber dos peticiones get por esto se pone una URI diferente
    @GetMapping("/{id}")
    //Se crea el atributo por si no se encuentra ninguna persona en la BD para que devuelva una respuesta en caso de que no exista la persona
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id){
        Optional<Persona> persona = personaRepository.findById(id);
        return persona.map(ResponseEntity::ok).orElseGet(() ->  ResponseEntity.notFound().build());
    }
    
    
    //Interfaces terceras pueda realizar peticiones como el front
    @CrossOrigin
    //Esta función se ejecuta cuando se ejecute POST
    @PostMapping
    //se deben crear los mismos atributos que tiene la persona (campos) y este metodo debe recepcionarlos
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona){
        Persona savedPersona = personaRepository.save(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersona);
    }
    
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id){
        if(!personaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        personaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona updatePersona){
        if(!personaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        updatePersona.setId(id);
        Persona savedPersona =  personaRepository.save(updatePersona);
        return ResponseEntity.ok(savedPersona);
    }
        
}
