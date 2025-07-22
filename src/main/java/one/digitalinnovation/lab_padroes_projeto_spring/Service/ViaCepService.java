package one.digitalinnovation.lab_padroes_projeto_spring.Service;

import one.digitalinnovation.lab_padroes_projeto_spring.Model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "viacep", url = "viacep.com.br/ws")
public interface ViaCepService {
    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json")
    Endereco consultar(@PathVariable("cep") String cep);
}
