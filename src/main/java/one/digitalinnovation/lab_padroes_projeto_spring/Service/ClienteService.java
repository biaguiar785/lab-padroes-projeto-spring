package one.digitalinnovation.lab_padroes_projeto_spring.Service;

import one.digitalinnovation.lab_padroes_projeto_spring.Model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void cadastrar(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);
}
