package com.spring.com.ms_product_service.controller;

import com.spring.com.ms_product_service.model.Product;
import com.spring.com.ms_product_service.service.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products/")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductoServiceImpl productoService;

    @GetMapping("/")
    public ResponseEntity<?> listar(){
        List<Product> optional = productoService.listarProducto();
        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "No hay elementos para mostrar"));
        } else {
            return ResponseEntity.ok(productoService.listarProducto());
        }
    } // ---


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Product> optional = productoService.buscarPorId(id);
        if (optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se encontro ese producto!!!"));
        }
    } //---


    @PostMapping("/")
    public ResponseEntity<?> crearProducto (@RequestBody Product product){
        try {
            Product newProductCreated = productoService.guardar(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(newProductCreated));
        } catch (Exception e){
            Map<String, Object> error = new HashMap<>();
            error.put("message", "No se pudo crear el producto");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    } //---

    @PutMapping("/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Product product, @PathVariable Long id){
        Optional<Product> optional = productoService.buscarPorId(id);
        if (optional.isPresent()){
            Product productoEditable = optional.get();

            productoEditable.setName(product.getName());
            productoEditable.setPrice(product.getPrice());

            return ResponseEntity.status(HttpStatus.OK).body(productoService.guardar(productoEditable));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "No se pudo hacer la actualizacion"));
        }
    } //---


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Optional<Product> optional = productoService.buscarPorId(id);
        if (optional.isPresent()){
            productoService.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Producto eliminado con exito!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Ese producto no existe"));
        }
    } //--

    @GetMapping("/buscar-producto-por-nombre/")
    public ResponseEntity<?> buscarProductoPorNombre(@RequestParam String name){
        List<Product> optional = productoService.findProductByName(name);
        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "No se encontro ese producto"));
        } else {
            return ResponseEntity.ok(optional);
        }
    } //--


    @GetMapping("/buscar-por-precio/{price}")
    public ResponseEntity<?> buscarPorPrecio(@PathVariable Double price) {
        Optional<Product> optional = productoService.findProductByPrice(price);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos con el precio especificado.");
        }
        return ResponseEntity.ok(optional);
    }


} // fin de la clase
