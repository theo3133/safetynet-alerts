package com.safetynet.alerts;

import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Test
    void shouldAddValidPerson() throws Exception {
        Person returnedPerson = new Person(
                "Jean",
                "Dupont",
                "10 rue de Paris",
                "Paris",
                "75000",
                "0102030405",
                "jean.dupont@test.fr"
        );

        when(personService.addPerson(any(Person.class)))
                .thenReturn(returnedPerson);

        String json = """
                {
                  "firstName": "Jean",
                  "lastName": "Dupont",
                  "address": "10 rue de Paris",
                  "city": "Paris",
                  "zip": "75000",
                  "phone": "0102030405",
                  "email": "jean.dupont@test.fr"
                }
                """;

        mockMvc.perform(
                        post("/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jean"))
                .andExpect(jsonPath("$.lastName").value("Dupont"));

        verify(personService).addPerson(any(Person.class));
    }

    @Test
    void shouldRejectPersonWithBlankFirstName() throws Exception {
        String json = """
                {
                  "firstName": " ",
                  "lastName": "Dupont",
                  "address": "10 rue de Paris",
                  "city": "Paris",
                  "zip": "75000",
                  "phone": "0102030405",
                  "email": "jean.dupont@test.fr"
                }
                """;

        mockMvc.perform(
                        post("/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isBadRequest());

        verifyNoInteractions(personService);
    }

    @Test
    void shouldRejectMissingPersonBody() throws Exception {
        mockMvc.perform(
                        post("/person")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());

        verifyNoInteractions(personService);
    }
}
