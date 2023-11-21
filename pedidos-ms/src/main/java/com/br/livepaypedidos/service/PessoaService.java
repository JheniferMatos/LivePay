package com.br.livepaypedidos.service;

import com.br.livepaypedidos.dto.PessoaDTO;
import com.br.livepaypedidos.model.Pessoa;
import com.br.livepaypedidos.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Serviço para operações relacionadas a pessoas.
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Obtém todas as pessoas paginadas.
     *
     * @param pageable A configuração da página.
     * @return Página de pessoas no formato DTO.
     */
    public Page<PessoaDTO> obterTodos(Pageable pageable){
        return pessoaRepository
                .findAll(pageable)
                .map(p -> modelMapper.map(p, PessoaDTO.class));
    }

    /**
     * Obtém uma pessoa por ID.
     *
     * @param id O ID da pessoa.
     * @return DTO representando a pessoa.
     * @throws EntityNotFoundException Se a pessoa não for encontrada.
     */
    public PessoaDTO obterPorId(Long id){
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    /**
     * Cria uma nova pessoa.
     *
     * @param pessoaDTO DTO contendo os detalhes da pessoa.
     * @return DTO representando a pessoa criada.
     */
    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO){
        Pessoa pessoa = modelMapper.map(pessoaDTO, Pessoa.class);
        pessoaRepository.save(pessoa);

        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    /**
     * Atualiza uma pessoa existente.
     *
     * @param id O ID da pessoa a ser atualizada.
     * @param dto DTO contendo os novos detalhes da pessoa.
     * @return DTO representando a pessoa atualizada.
     */
    public PessoaDTO atualizarPessoa(Long id, PessoaDTO dto) {
        Pessoa pessoa = modelMapper.map(dto, Pessoa.class);
        pessoa.setId(id);
        pessoa = pessoaRepository.save(pessoa);
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    /**
     * Exclui uma pessoa por ID.
     *
     * @param id O ID da pessoa a ser excluída.
     */
    public void excluirPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}
