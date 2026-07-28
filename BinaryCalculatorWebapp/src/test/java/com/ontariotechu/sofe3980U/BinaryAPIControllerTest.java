package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
@Test
public void multiplyStringTest() throws Exception {
    this.mvc.perform(get("/multiply?operand1=111&operand2=1010"))
        .andExpect(status().isOk())
        .andExpect(content().string("1000110"));
}

@Test
public void andStringTest() throws Exception {
    this.mvc.perform(get("/and?operand1=1100&operand2=1010"))
        .andExpect(status().isOk())
        .andExpect(content().string("1000"));
}

@Test
public void orJsonTest() throws Exception {
    this.mvc.perform(get("/or_json?operand1=1100&operand2=1010"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.operand1").value("1100"))
        .andExpect(jsonPath("$.operator").value("or"))
        .andExpect(jsonPath("$.operand2").value("1010"))
        .andExpect(jsonPath("$.result").value("1110"));
}
@Test
public void multiplyByZeroStringTest() throws Exception {
    this.mvc.perform(get("/multiply?operand1=0&operand2=1010"))
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
}

@Test
public void multiplyJsonTest() throws Exception {
    this.mvc.perform(get("/multiply_json?operand1=111&operand2=1010"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.operand1").value("111"))
        .andExpect(jsonPath("$.operator").value("multiply"))
        .andExpect(jsonPath("$.operand2").value("1010"))
        .andExpect(jsonPath("$.result").value("1000110"));
}

@Test
public void andWithZeroStringTest() throws Exception {
    this.mvc.perform(get("/and?operand1=0000&operand2=1111"))
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
}

@Test
public void andJsonTest() throws Exception {
    this.mvc.perform(get("/and_json?operand1=1100&operand2=1010"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.operand1").value("1100"))
        .andExpect(jsonPath("$.operator").value("and"))
        .andExpect(jsonPath("$.operand2").value("1010"))
        .andExpect(jsonPath("$.result").value("1000"));
}

@Test
public void orDifferentLengthStringTest() throws Exception {
    this.mvc.perform(get("/or?operand1=1&operand2=1000"))
        .andExpect(status().isOk())
        .andExpect(content().string("1001"));
}

@Test
public void orWithZeroJsonTest() throws Exception {
    this.mvc.perform(get("/or_json?operand1=1111&operand2=0"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.operand1").value("1111"))
        .andExpect(jsonPath("$.operator").value("or"))
        .andExpect(jsonPath("$.operand2").value("0"))
        .andExpect(jsonPath("$.result").value("1111"));
}
@Test
public void addZeroTest() throws Exception {
    this.mvc.perform(get("/add").param("operand1", "0").param("operand2", "0"))
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
}

@Test
public void addCarryTest() throws Exception {
    this.mvc.perform(get("/add").param("operand1", "111").param("operand2", "1"))
        .andExpect(status().isOk())
        .andExpect(content().string("1000"));
}

@Test
public void addJsonDifferentLengthTest() throws Exception {
    this.mvc.perform(get("/add_json").param("operand1", "1").param("operand2", "1010"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.operand1").value("1"))
        .andExpect(jsonPath("$.operator").value("add"))
        .andExpect(jsonPath("$.operand2").value("1010"))
        .andExpect(jsonPath("$.result").value("1011"));
}
}