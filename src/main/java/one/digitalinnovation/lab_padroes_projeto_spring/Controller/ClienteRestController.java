package one.digitalinnovation.lab_padroes_projeto_spring.Controller;

import one.digitalinnovation.lab_padroes_projeto_spring.Model.Cliente;
import one.digitalinnovation.lab_padroes_projeto_spring.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<String> inserir(@RequestBody Cliente cliente) {
        clienteService.cadastrar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("cliente " + cliente.getNome() + " salvo!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.atualizar(id, cliente);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente " + cliente.getNome() + " atualizado!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente " + id + " excluído!");
    }
}
