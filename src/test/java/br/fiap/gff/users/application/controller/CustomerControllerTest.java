package br.fiap.gff.users.application.controller;

import br.fiap.gff.users.domain.entities.Address;
import br.fiap.gff.users.domain.entities.Customer;
import br.fiap.gff.users.domain.entities.Phone;
import br.fiap.gff.users.domain.exceptions.CustomerException;
import br.fiap.gff.users.domain.usecases.CustomerUseCase;
import br.fiap.gff.users.infra.config.ErrorHandler;
import br.fiap.gff.users.utils.UsuarioHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CustomerUseCase customerUsecase;
    ;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        CustomerController mensagemController = new CustomerController(customerUsecase);
        mockMvc = MockMvcBuilders.standaloneSetup(mensagemController)
                .setControllerAdvice(new ErrorHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Test
    void devePermitirRegistrarUsuario() throws Exception {
        var usuarioRequest = UsuarioHelper.gerarUsuarioRequest();
        when(customerUsecase.create(any(Customer.class)))
                .thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(post("/v1/users/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usuarioRequest)))
//                    .andDo(print())
                .andExpect(status().isOk());
        verify(customerUsecase, times(1))
                .create(any(Customer.class));
    }

    @Test
    void devePermitirAdicionarEndereco() throws Exception {
        var enderecoRequest = UsuarioHelper.gerarEnderecoRequest();
        var id = 1l;
        var customer = UsuarioHelper.createCustomer(id);

        when(customerUsecase.getById(any(Long.class))).thenReturn(customer);

        when(customerUsecase.addAddress(any(Long.class), any(Address.class))).thenReturn(customer);

        mockMvc.perform(post("/v1/users/customer/{id}/address", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(enderecoRequest)))
//                    .andDo(print())
                .andExpect(status().isOk());
        verify(customerUsecase, times(1))
                .addAddress(any(Long.class), any(Address.class));
    }

    @Test
    void devePermitirAdicionarTelefone() throws Exception {
        var telefoneRequest = UsuarioHelper.gerarTelefoneRequest();
        var id = 1l;
        var customer = UsuarioHelper.createCustomer(id);

        when(customerUsecase.getById(any(Long.class))).thenReturn(customer);

        when(customerUsecase.addPhone(any(Long.class), any(Phone.class))).thenReturn(customer);

        mockMvc.perform(post("/v1/users/customer/{id}/phone", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(telefoneRequest)))
//                    .andDo(print())
                .andExpect(status().isOk());
        verify(customerUsecase, times(1))
                .addPhone(any(Long.class), any(Phone.class));
    }


    @Test
    void devePermitirBuscarUsuario() throws Exception {
        var id = 1l;
        var customer = UsuarioHelper.createCustomer(id);

        when(this.customerUsecase.getById(any(Long.class))).thenReturn(customer);

        mockMvc.perform(get("/v1/users/customer/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(this.customerUsecase, times(1)).getById(any(Long.class));
    }

    @Test
    void deveGerarExcecao_QuandoBuscarCliente_IdNaoExistente()
            throws Exception {

        var id = 1l;
        when(customerUsecase.getById(any(Long.class)))
                .thenThrow(new CustomerException(String.format("Customer of id %d not found!", id)));

        mockMvc.perform(get("/v1/users/customer/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
//          .andDo(print())
                .andExpect(status().is4xxClientError());
        verify(customerUsecase, times(1))
                .getById(any(Long.class));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}