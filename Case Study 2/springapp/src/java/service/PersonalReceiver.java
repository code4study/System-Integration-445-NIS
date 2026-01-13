///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import springapp.web.controller.EmployeeController;
//import springapp.web.model.Employee;
//import springapp.web.dao.EmployeeDao;
//
///**
// *
// * @author bluez
// */
//@Component
//public class PersonalReceiver {
//
//    @Autowired
//    private EmployeeDao employeeRepository;
//    @Autowired 
//    EmployeeController emC = new EmployeeController();
//    // add
//    public void receiveMessage(String json) {
//        try {
//            System.out.println("DA NHAN MESSAGE FROM HR 19335");
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode node = mapper.readTree(json);
//            int currentCount = employeeRepository.getEmployeeCount();
//            int startIndex = currentCount + 1;
//
//            int idEmployee = node.get("idEmployee").asInt();
//            String firstName = node.get("firstName").asText();
//            String lastName = node.get("lastName").asText();
//
//            Employee emp = new Employee();
//            emp.setEmployeeNumber(1000 + idEmployee);
//            emp.setIdEmployee(idEmployee);
//            emp.setFirstName(firstName);
//            emp.setLastName(lastName);
//            emp.setSsn(100000000L + startIndex);
//
//            employeeRepository.insert(emp);
//            
//            emC.clearEmployeeCache();
//            
//             try {
//                RestTemplate rest = new RestTemplate();
//                String cacheUrl = "http://localhost:8888/springapp_show/admin/EPerson/clearCache";
//                rest.getForObject(cacheUrl, String.class);
//                System.out.println("Da xoa cache");
//
//            } catch (Exception e) {
//                System.err.println("Loi khi xoa cache" + e.getMessage());
//            }
//
//            System.out.println("Đã tạo Employee từ HR: " + emp.getFullName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
