package com.springboot.cloud.service.Impl;

import com.springboot.cloud.util.Response;
import com.springboot.cloud.utils.CodeGenerateUtils;
import com.springboot.cloud.dto.ColumnDto;
import com.springboot.cloud.dto.TableDto;
import com.springboot.cloud.service.CodeGenerateService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeGenerateServiceImpl implements CodeGenerateService {
    private static String entityPath;
    private static List<ColumnDto> columnDtos = new ArrayList<>();
    private static final String TEMPLATE_PATH = "provide-api/code-generate-api/src/main/java/com/springboot/cloud/template";
    private void generateDir(String entityName) throws IOException {
        String basePath = new File("").getAbsolutePath();
        entityPath = basePath + "/provide-api/" + entityName + "-api";
        File entityFile = new File(entityPath);
        System.out.println(entityFile.getAbsolutePath());
        entityFile.mkdirs();
        //创建pom，service，controller，entity，repository的文件夹
        new File(entityFile + "/pom.xml").createNewFile();
        new File(entityFile + "/src/main/resources").mkdirs();
        new File(entityFile + "/src/main/java/com/generate/code/controller/").mkdirs();
        new File(entityFile + "/src/main/java/com/generate/code/service/").mkdirs();
        new File(entityFile + "/src/main/java/com/generate/code/service/impl").mkdirs();
        new File(entityFile + "/src/main/java/com/generate/code/repository").mkdirs();
        new File(entityFile + "/src/main/java/com/generate/code/entity/").mkdirs();
    }
    public Response genereateCode(TableDto tableDto, HttpHeaders header) {
        getEntityPath(tableDto.getTableName());
        generatePom(tableDto.getTableName());
        generateApplication(tableDto.getTableName());
        generateEntity(tableDto);
        generateRepository(tableDto);
        generateService(tableDto);
        generateServiceImpl(tableDto);
        generateController(tableDto);
        generateMain(tableDto.getTableName());
        return new Response<>(1,"sucess","generate code successfully");
    }
    private void getEntityPath(String entityName) {
        String basePath = new File("").getAbsolutePath();
        entityPath = basePath + "/provide-api/" + entityName + "-api";
        new File(entityPath).mkdirs();
    }
    private void generatePom(String entityName){
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("name", entityName + "-api");
            Template template = configuration.getTemplate("PomTemplate.ftl");
            File docFile = new File(entityPath + "/pom.xml");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void generateApplication(String entityName) {
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            new File(entityPath + "/src/main/resources").mkdirs();
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Map<String, Object> dataMap = new HashMap<String, Object>();
            Integer port = 10000 + entityName.charAt(0) - 'a';
            dataMap.put("name", entityName + "-api");
            dataMap.put("port", port.toString());
            Template template = configuration.getTemplate("ApplicationTemplate.ftl");
            File docFile = new File(entityPath + "/src/main/resources/application.yml");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void generateEntity(TableDto tableDto) {
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            new File(entityPath + "/src/main/java/com/generate/code/entity/").mkdirs();
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("tableName", tableDto.getTableName());
            dataMap.put("name", CodeGenerateUtils.toFirstUpperCase(tableDto.getTableName()));
            List<ColumnDto> columnDtoList = tableDto.getColumnDtoList();
            List<ColumnDto> columnDtoList_new = new ArrayList<>(columnDtoList);
            for (ColumnDto c : columnDtoList_new) {
                if (c.getIsKey() == true) {
                    dataMap.put("type", c.getType());
                    dataMap.put("columnName", c.getColumnName());
                    columnDtoList_new.remove(c);
                    break;
                }
            }
            dataMap.put("columnList", columnDtoList_new);
            Template template = configuration.getTemplate("EntityTemplate.ftl");
            File docFile = new File(entityPath + "/src/main/java/com/generate/code/entity/"
                    + CodeGenerateUtils.toFirstUpperCase(tableDto.getTableName()) + ".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void generateRepository(TableDto tableDto) {
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            new File(entityPath + "/src/main/java/com/generate/code/repository/").mkdirs();
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("name", CodeGenerateUtils.toFirstUpperCase(tableDto.getTableName()));
            List<ColumnDto> columnDtoList = tableDto.getColumnDtoList();
            for (ColumnDto c : columnDtoList) {
                if (c.getIsQuery())
                    columnDtos.add(c);
                c.setColumnName(CodeGenerateUtils.toFirstUpperCase(c.getColumnName()));
            }
            dataMap.put("columnList", columnDtos);
            Template template = configuration.getTemplate("RepositoryTemplate.ftl");
            File docFile = new File(entityPath + "/src/main/java/com/generate/code/repository/"
                    + CodeGenerateUtils.toFirstUpperCase(tableDto.getTableName()) + "Repository.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void generateService(TableDto tableDto) {
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            new File(entityPath + "/src/main/java/com/generate/code/service/").mkdirs();
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("name", CodeGenerateUtils.toFirstUpperCase(tableDto.getTableName()));
            dataMap.put("columnList", columnDtos);
            Template template = configuration.getTemplate("ServiceTemplate.ftl");
            File docFile = new File(entityPath + "/src/main/java/com/generate/code/service/"
                    + CodeGenerateUtils.toFirstUpperCase(tableDto.getTableName()) + "Service.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateServiceImpl(TableDto tableDto) {
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            new File(entityPath + "/src/main/java/com/generate/code/service/impl").mkdirs();
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("name", CodeGenerateUtils.toFirstUpperCase(tableDto.getTableName()));
            dataMap.put("columnList", columnDtos);
            Template template = configuration.getTemplate("ServiceImplTemplate.ftl");
            File docFile = new File(entityPath + "/src/main/java/com/generate/code/service/impl/"
                    + CodeGenerateUtils.toFirstUpperCase(tableDto.getTableName()) + "ServiceImpl.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void generateController(TableDto tableDto) {
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            new File(entityPath + "/src/main/java/com/generate/code/controller").mkdirs();
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("name", CodeGenerateUtils.toFirstUpperCase(tableDto.getTableName()));
            dataMap.put("columnList", columnDtos);
            Template template = configuration.getTemplate("ControllerTemplate.ftl");
            File docFile = new File(entityPath + "/src/main/java/com/generate/code/controller/"
                    + CodeGenerateUtils.toFirstUpperCase(tableDto.getTableName()) + "Controller.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void generateMain(String name) {
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("name", CodeGenerateUtils.toFirstUpperCase(name));
            Template template = configuration.getTemplate("MainTemplate.ftl");
            File docFile = new File(entityPath + "/src/main/java/com/generate/code/"
                    + CodeGenerateUtils.toFirstUpperCase(name) + "Application.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        CodeGenerateServiceImpl codeGenerateService = new CodeGenerateServiceImpl();
        codeGenerateService.getEntityPath("card");
        codeGenerateService.generatePom("card");
        codeGenerateService.generateApplication("card");
        TableDto tableDto = new TableDto();
        tableDto.setTableName("card");
        List<ColumnDto> columnDtoList = new ArrayList<>();
        columnDtoList.add(new ColumnDto("String", "cardid", true, true));
        columnDtoList.add(new ColumnDto("String", "password", false, false));
        columnDtoList.add(new ColumnDto("String", "name", false, true));
        tableDto.setColumnDtoList(columnDtoList);
        codeGenerateService.generateEntity(tableDto);
        codeGenerateService.generateRepository(tableDto);
        codeGenerateService.generateService(tableDto);
        codeGenerateService.generateServiceImpl(tableDto);
        codeGenerateService.generateController(tableDto);
        codeGenerateService.generateMain("card");
    }
}
