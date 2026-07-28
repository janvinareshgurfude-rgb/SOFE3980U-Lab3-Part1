package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }
@Test
public void multiplyOperatorTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "111").param("operand2", "1010").param("operator", "*"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1000110"));
}

@Test
public void andOperatorTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1100").param("operand2", "1010").param("operator", "&"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1000"));
}

@Test
public void orOperatorTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1100").param("operand2", "1010").param("operator", "|"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1110"));
}
@Test
public void multiplyByZeroTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "0").param("operand2", "1010").param("operator", "*"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "0"));
}

@Test
public void multiplyLeadingZerosTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "0011").param("operand2", "101").param("operator", "*"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1111"));
}

@Test
public void andWithZeroTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "0000").param("operand2", "1111").param("operator", "&"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "0"));
}

@Test
public void andDifferentLengthTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "101").param("operand2", "1").param("operator", "&"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1"));
}

@Test
public void orWithZeroTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1111").param("operand2", "0").param("operator", "|"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1111"));
}

@Test
public void orDifferentLengthTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1").param("operand2", "1000").param("operator", "|"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1001"));
}
@Test
public void getParameterEmptyTest() throws Exception {
    this.mvc.perform(get("/").param("operand1", ""))
        .andExpect(status().isOk())
        .andExpect(view().name("calculator"))
        .andExpect(model().attribute("operand1", ""))
        .andExpect(model().attribute("operand1Focused", false));
}

@Test
public void postAdditionZeroTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "0").param("operand2", "0").param("operator", "+"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "0"));
}

@Test
public void postAdditionCarryTest() throws Exception {
    this.mvc.perform(post("/").param("operand1", "111").param("operand2", "1").param("operator", "+"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1000"));
}

}