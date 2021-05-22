package PB.WebServiceProject.rest;

import PB.WebServiceProject.rest.dto.ClientDTO;
import PB.WebServiceProject.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(description = "Get all clients")
    @GetMapping("/clients")
    public List<ClientDTO> getClients(@Parameter(description = "get clients")
                                      @RequestParam(name = "name", required = false) String name) {
        return clientService.findClients(name);
    }

    @Operation(description = "Add new client")
    @PostMapping("/client")
    public ClientDTO addClient(@Parameter(description = "add new client")
                               @Valid @RequestBody ClientDTO clientDTO) {    // + adnotacja @Valid
        return clientService.addClient(clientDTO);
    }

    @Operation(description = "Delete client by id")
    @DeleteMapping("/client/{id}")
    public ResponseEntity deleteClient(@Parameter(description = "delete client by id")
                                       @PathVariable(name = "id") Long id) { // tam gdzie jest @PathVariable jest obiekt ResponseEntity
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "find client by id")
    @GetMapping("/client/{id}")
    public ResponseEntity getClientById(@Parameter(description = "get client by id")
                                        @PathVariable(name = "id") Long id) {
        Optional<ClientDTO> getClientById = clientService.getClientById(id);
        if (getClientById.isPresent()) {
            return ResponseEntity.ok(getClientById.get());

        }
        return ResponseEntity.noContent().build();// http no content 204 // noContent().build() co to oznacza?

    }
//    @Operation(description = "find client by name")
//    @GetMapping("/client/{name}")
//    public ResponseEntity findClientsByName(@PathVariable(name = "name") String name) {
//           //     List<ClientDTO> findClientsByName = clientService.findClients(name);
//        return ResponseEntity.ok(clientService.findClients(name));
//
//    }
}
