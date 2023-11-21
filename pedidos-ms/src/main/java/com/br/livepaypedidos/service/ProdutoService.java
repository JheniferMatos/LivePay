package com.br.livepaypedidos.service;

import com.br.livepaypedidos.dto.ProdutoDTO;
import com.br.livepaypedidos.model.Produto;
import com.br.livepaypedidos.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Serviço para operações relacionadas a produtos.
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Obtém todos os produtos paginados.
     *
     * @param pageable A configuração da página.
     * @return Página de produtos no formato DTO.
     */
    public Page<ProdutoDTO> obterTodos(Pageable pageable){
        return produtoRepository
                .findAll(pageable)
                .map(p -> modelMapper.map(p, ProdutoDTO.class));
    }

    /**
     * Obtém um produto por ID.
     *
     * @param id O ID do produto.
     * @return DTO representando o produto.
     * @throws EntityNotFoundException Se o produto não for encontrado.
     */
    public ProdutoDTO obterPorId(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(produto, ProdutoDTO.class);
    }

    /**
     * Cria um novo produto.
     *
     * @param produtoDTO DTO contendo os detalhes do produto.
     * @return DTO representando o produto criado.
     */
    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO){
        Produto produto = modelMapper.map(produtoDTO, Produto.class);
        produtoRepository.save(produto);

        return modelMapper.map(produto, ProdutoDTO.class);
    }

    /**
     * Atualiza um produto existente.
     *
     * @param id O ID do produto a ser atualizado.
     * @param dto DTO contendo os novos detalhes do produto.
     * @return DTO representando o produto atualizado.
     */
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO dto) {
        Produto produto = modelMapper.map(dto, Produto.class);
        produto.setId(id);
        produto = produtoRepository.save(produto);
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    /**
     * Exclui um produto por ID.
     *
     * @param id O ID do produto a ser excluído.
     */
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

}
