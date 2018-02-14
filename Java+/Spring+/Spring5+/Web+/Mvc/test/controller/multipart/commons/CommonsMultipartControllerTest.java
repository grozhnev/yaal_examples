package controller.multipart.commons;

import common.BaseTest;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static controller.multipart.commons.CommonsMultipartController.DATA_PART_NAME;
import static controller.multipart.commons.CommonsMultipartController.JSON_DATA_PART_NAME;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {CommonsMultipartController.class, CommonsMultipartControllerTest.Config.class})
public class CommonsMultipartControllerTest extends BaseTest {

    @Test
    public void requestParam() throws Exception {
        String filename1 = "file1.json";
        String fileContent1 = "file content 1";
        MockMultipartFile multipartFile1 =
                new MockMultipartFile(DATA_PART_NAME, filename1, APPLICATION_JSON_VALUE, fileContent1.getBytes());

        String filename2 = "file2.json";
        String fileContent2 = "file content 2";
        MockMultipartFile multipartFile2 =
                new MockMultipartFile(DATA_PART_NAME, filename2, APPLICATION_JSON_VALUE, fileContent2.getBytes());

        String filename3 = "file3.json";
        String fileContent3 = "{\"name\": \"John\"}";
        MockMultipartFile multipartFile3 =
                new MockMultipartFile(JSON_DATA_PART_NAME, filename3, APPLICATION_JSON_VALUE, fileContent3.getBytes());

        MockMultipartHttpServletRequestBuilder multipartBuilder = MockMvcRequestBuilders
                .fileUpload(CommonsMultipartController.ENDPOINT)
                .file(multipartFile1)
                .file(multipartFile2)
                .file(multipartFile3);

        String paramValue = "4";

        String expContent = fileContent1 + fileContent2 + "John" + paramValue;

        mvc.perform(multipartBuilder.param(CommonsMultipartController.PARAM_NAME, paramValue))
                .andExpect(status().isOk())
                .andExpect(content().string(expContent));
    }

    @EnableWebMvc
    @Configuration
    static class Config {
        @Bean
        CommonsMultipartResolver commonsMultipartResolver() {
            return new CommonsMultipartResolver();
        }
    }
}