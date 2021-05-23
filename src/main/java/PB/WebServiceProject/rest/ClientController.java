package PB.WebServiceProject.rest;

import PB.WebServiceProject.rest.dto.ClientDTO;
import PB.WebServiceProject.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

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

    @Operation(description = "edit client")
    @PutMapping("/client/{id}")
    public ClientDTO editClient(@Parameter(description = "edit client by id")
                                @PathVariable(name = "id") Long id,
                                @Valid @RequestBody ClientDTO clientDTO) {
        return clientService.editClient(id, clientDTO);
    }
}
