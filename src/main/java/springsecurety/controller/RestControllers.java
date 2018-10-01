package springsecurety.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import springsecurety.model.Emp;
import springsecurety.model.Managers;
import springsecurety.services.ManagersService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SOFTWARE02 on 01.09.2018.
 */

@RestController
public class RestControllers {

    @Autowired
    ManagersService managersService;

    @GetMapping("Managers")
    public List<Managers> getManagers(){
       return managersService.getAllManagers();
    }

    @GetMapping("deleteManager")
    public List<Managers> deleteManagers(@RequestParam Integer id){
        return managersService.deleteManagers(id);

    }

    @GetMapping("saveManager")
    public List<Managers> saveManagers(@RequestParam String name, String lastname, String position){
      Managers managers = new Managers(name,lastname,position,null);
      return   managersService.addManagers(managers);
    }



    @Autowired
    DiscoveryClient discoveryClient;


    @GetMapping("/getEmp")
    public  ResponseEntity getEmployee(){


        List<ServiceInstance> instances = discoveryClient.getInstances("employee-zuul-service");
        ServiceInstance serviceInstance = instances.get(0);
        System.out.println(serviceInstance.getUri());



        String baseUrl = serviceInstance.getUri().toString();

        baseUrl+= "/producer/getEmployees";

        //String baseUrl = "http://localhost:8282/getEmployees";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = null;

        try{
            responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(),String.class);
        }
        catch (Exception ex){

        }

        System.out.println(responseEntity.getBody());

        List<Emp> mm = new ArrayList<Emp>();


        String[] marray = responseEntity.getBody().split(",");

        ResponseEntity<String> mresponseEntity = new ResponseEntity<>(responseEntity.getBody(),
                responseEntity.getHeaders(),
                HttpStatus.OK);

        return  mresponseEntity;
    }

    @GetMapping("/getVeh")
    public  ResponseEntity getVehicle(@RequestParam("observer") String observer){


        List<ServiceInstance> instances = discoveryClient.getInstances("employee-zuul-service");
        ServiceInstance serviceInstance = instances.get(0);
        System.out.println(serviceInstance.getUri());



        String baseUrl = serviceInstance.getUri().toString();

        baseUrl+= "/producer/getVehicle";


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = null;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("observer", observer);

        try{
            responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, getHeaders(),String.class);
        }
        catch (Exception ex){

        }

        System.out.println(responseEntity.getBody());

        List<Emp> mm = new ArrayList<Emp>();


        String[] marray = responseEntity.getBody().split(",");

        ResponseEntity<String> mresponseEntity = new ResponseEntity<>(responseEntity.getBody(),
                responseEntity.getHeaders(),
                HttpStatus.OK);

        return  mresponseEntity;
    }


    private HttpEntity<Object> getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(httpHeaders);
    }


}
