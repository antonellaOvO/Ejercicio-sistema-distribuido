/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermartdistributed;

// Importa las anotaciones y clases necesarias de Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;



/**
 *
 * @author anto_
 */
// Marca la clase como un controlador REST, lo que significa que manejará las solicitudes HTTP
@RestController
// Define la ruta base para las solicitudes HTTP manejadas por este controlador
@RequestMapping("/personas")
public class PersonasController {
    
    // Inyecta una instancia de PedidoRepository, que proporciona métodos para interactuar con la base de datos
    @Autowired
    private PersonasRepository personaRepository;
    
    @Autowired 
    private AdministradoresRepository administradorRepository;
    
    @Autowired 
    private UsuariosRepository usuarioRepository;
    
    
    
    // Maneja las solicitudes GET a /pedidos para obtener una lista de todas los pedidos
    @GetMapping
    public List<Personas> getAllPersonas() {
        // Llama al método findAll() del repositorio para obtener todas los pedidos
        return personaRepository.findAll();
    }

    // Maneja las solicitudes GET a /pedidos/{id} para obtener un pedido específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Personas> getPersonaById(@PathVariable int id) { // ResponseEntity es una clase de Spring que representa toda la respuesta HTTP: el estado, los encabezados y el cuerpo
        
        // Busca un pedido por su ID
        Optional<Personas> persona = personaRepository.findById(id);
        // Si se encuentra el pedido, devuelve una respuesta HTTP 200 (OK) con el pedido encontrado
        if (persona.isPresent()) { //isPresent(): Devuelve true si el Optional contiene un valor, o false si está vacío.
            return ResponseEntity.ok(persona.get());  
         // El método ok es un método estático de ResponseEntity que crea una instancia de ResponseEntity con un estado HTTP 200 (OK) y el cuerpo de la respuesta configurado con el valor proporcionado
        // Si el Optional contiene un valor (es decir, no está vacío), get() devolverá ese valor
        } else {
            // Si no se encuentra el pedido, devuelve una respuesta HTTP 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
    

    // Maneja las solicitudes POST a /pedidos para crear un nuevo pedido
   /* @PostMapping
    public Personas createPersona(@RequestBody Personas persona) {
         // Guarda el nuevo pedido en la base de datos y lo devuelve en la respuesta
        return personaRepository.save(persona);
    }
*/
    // Maneja las solicitudes PUT a /pedidos/{id} para actualizar un pedido existente
    @PutMapping("actualizar-nombre-correo/{id}")
    public ResponseEntity<Personas> updatePedido(@PathVariable int id, @RequestBody Personas personaDetails) {
        // Busca un pedido por su ID
        Optional<Personas> persona = personaRepository.findById(id);
        // Si se encuentra el pedido, actualiza sus detalles y lo guarda
        if (persona.isPresent()) { //isPresent(): Devuelve true si el Optional contiene un valor, o false si está vacío.
            Personas personaToUpdate = persona.get();
            // Actualiza el idTarjeta y el idUsuario del pedido con los detalles proporcionados en la solicitud
            personaToUpdate.setNombre(personaDetails.getNombre());
            personaToUpdate.setCorreo(personaDetails.getCorreo());
            // Guarda el pedido actualizado y devuelve una respuesta HTTP 200 (OK)
            return ResponseEntity.ok(personaRepository.save(personaToUpdate));
        } else {
            // Si no se encuentra el pedido, devuelve una respuesta HTTP 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
    
    

    // Maneja las solicitudes DELETE a /pedidos/{id} para eliminar un pedido por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable int id) {
         // Maneja las solicitudes DELETE a /pedidos/{id} para eliminar un pedido por su ID
        Optional<Personas> persona = personaRepository.findById(id);
        // Si se encuentra el pedido, la elimina de la base de datos
        if (persona.isPresent()) { //isPresent(): Devuelve true si el Optional contiene un valor, o false si está vacío.
            personaRepository.delete(persona.get());
            // Devuelve una respuesta HTTP 200 (OK) sin contenido
            return ResponseEntity.ok().build();
            //build() se utiliza para construir una instancia de ResponseEntity sin cuerpo(body)
        } else {
            // Si no se encuentra el pedido, devuelve una respuesta HTTP 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Personas> createPersona(@RequestBody Personas persona){
        Personas savedPersona = personaRepository.save(persona); /*insert into Personas (apellido_persona, contrasena_persona, correo_persona, direccion_persona, DNI, fechaCreacion_persona, fechaNacimiento_persona, nombre_persona, telefono_persona, tipo, id_persona) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)*/
        
        if("Administrador".equals(persona.getTipo())){
            Administradores admin = new Administradores();
            admin.setId(savedPersona.getIdPersona());
            administradorRepository.save(admin); /*insert into Administradores (id_administrador) values (?), aquí el ? = user*/  
        } else if("Usuario".equals(persona.getTipo())){
            Usuarios user = new Usuarios();
            user.setIdUsuario(savedPersona.getIdPersona());
            usuarioRepository.save(user); /*insert into Usuarios (id_usuario) values (?), aquí ? = admin*/
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersona);
    }
    
    
    
    
    
}
