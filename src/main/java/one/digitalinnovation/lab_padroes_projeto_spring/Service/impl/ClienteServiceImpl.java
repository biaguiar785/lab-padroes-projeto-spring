package one.digitalinnovation.lab_padroes_projeto_spring.Service.impl;

import one.digitalinnovation.lab_padroes_projeto_spring.Model.Cliente;
import one.digitalinnovation.lab_padroes_projeto_spring.Model.Endereco;
import one.digitalinnovation.lab_padroes_projeto_spring.Repository.ClienteRepository;
import one.digitalinnovation.lab_padroes_projeto_spring.Repository.EnderecoRepository;
import one.digitalinnovation.lab_padroes_projeto_spring.Service.ClienteService;
import one.digitalinnovation.lab_padroes_projeto_spring.Service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void cadastrar(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(
                        () -> {
                            Endereco novoEndereco = viaCepService.consultar(cep);
                            enderecoRepository.save(novoEndereco);
                            return novoEndereco;
                        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            cliente.setId(clienteBd.get().getId());
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
