package com.fintech.FintechApp.Controllers;

import com.fintech.FintechApp.Models.Client;
import com.fintech.FintechApp.Repositories.ClientRepository;
import com.fintech.FintechApp.Services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    IClientService clientService;


    @GetMapping("/all")
    public List<Client> findAll(){

        return clientService.mostrarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable long id){
        Optional<Client> cliente = clientService.findById(id);

        if(cliente.isPresent()){
            return new ResponseEntity<>(cliente.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Client input) {

        Optional<Client> optionalCliente = clientService.findById(id);

        if (optionalCliente.isPresent()) {
            Client cliente = optionalCliente.get();

            cliente.setCedula(input.getCedula());
            cliente.setName(input.getName());
            cliente.setLastName(input.getLastName());
            cliente.setEmail(input.getEmail());
            cliente.setPass(input.getPass());
            cliente.setPhone(input.getPhone());
            cliente.setStatus(input.getStatus());
            cliente.setLocation_Id(input.getLocation_Id());

            Client salvar = clientService.crearCliente(cliente);

            return new ResponseEntity<>(salvar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/create")
    public ResponseEntity<Client> agregarCliente(@RequestBody Client cliente){
        clientService.crearCliente(cliente);
        return new ResponseEntity<>(cliente,HttpStatus.OK);
    }

}