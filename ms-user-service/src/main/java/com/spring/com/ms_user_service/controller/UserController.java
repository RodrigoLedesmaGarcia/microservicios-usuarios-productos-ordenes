package com.spring.com.ms_user_service.controller;

import com.spring.com.ms_user_service.model.User;
import com.spring.com.ms_user_service.service.UserServiceImpl;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/users/")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // http:localhost:6002/api/users/
    @GetMapping("/")
    public ResponseEntity<?> listarUsuarios(){
    List<User> optional = userService.listar();
        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Lista vacia, No hay elementos para mostrar!!!"));
        } else {
            return ResponseEntity.ok(userService.listar());
        }
    } //---

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorIdUsuario(@PathVariable Long id){
        Optional<User> optional = userService.buscarPorId(id);
        if (optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se pudo encontrar ese usuario!!!"));
        }
    } //---


    @PostMapping("/")
    public ResponseEntity<?> crearUsuario(@RequestBody User user){
        try {
            User newUserCreated = userService.guardar(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardar(newUserCreated));
        } catch (Exception e){
            Map<String, Object> errores = new HashMap<>();
            errores.put("message", "Ocurrio un error al crear el usuario!!!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
    } // ---

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@RequestBody User user, @PathVariable Long id){
        Optional<User> optional = userService.buscarPorId(id);
        if (optional.isPresent()){
            User editableUser = optional.get();

            editableUser.setName(user.getName());
            return ResponseEntity.status(HttpStatus.OK).body(userService.guardar(editableUser));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "No se pudo editar ese usario"));
        }
    } //---

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        Optional<User> optional = userService.buscarPorId(id);
        if (optional.isPresent()){
            userService.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Usuario borrado con exito!!!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "No se pudo borrar el registro"));
        }
    } //---


    @GetMapping("/buscar-por-nombre")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String name){
        List<User> optional = userService.findUserByName(name);
        if (optional.isEmpty()){
            return ResponseEntity.status(404).body(Map.of("message", "No se encontro el usuario " + name));
        } else {
            return ResponseEntity.ok(optional);
        }
    } //---




} // fin de la clase
