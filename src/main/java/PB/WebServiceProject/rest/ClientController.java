package PB.WebServiceProject.rest;

import PB.WebServiceProject.rest.dto.ClientDTO;
import PB.WebServiceProject.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
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
    public List<ClientDTO> getClients(@RequestParam(name = "name", required = false) String name) {
        return clientService.findClients(name);
    }
    @Operation(description = "Add new client") // + adnotacja @Valid
    @PostMapping("/client")
    public ClientDTO addClient(@Valid @RequestBody ClientDTO clientDTO) {
        return clientService.addClient(clientDTO);
    }

    @Operation(description = "Delete client by id")
    @DeleteMapping("/client/{id}")
    public ResponseEntity deleteClient(@PathVariable(name = "id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
    @Operation(description = "find client by id")
    @GetMapping("/client/{id}")
    public ResponseEntity getClientById(@PathVariable(name = "id") Long id) {
        Optional<ClientDTO> getClientById = clientService.getClientById(id);
        if (getClientById.isPresent()) {
            return ResponseEntity.ok(getClientById.get());

        }
        return ResponseEntity.noContent().build();// http no content 204

    }
}
