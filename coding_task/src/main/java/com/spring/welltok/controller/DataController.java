package com.spring.welltok.controller;

import com.spring.welltok.model.Data;
import com.spring.welltok.repository.DataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DataController {

    @Autowired
    DataRepository dataRepository;


    @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
    public String getHealthCheck()
    {
        return "{ \"isWorking\" : true }";
    }


    @GetMapping("/data")
    public List<Data> getData()
    {
        Iterable<Data> result = dataRepository.findAll();
        List<Data> dataList = new ArrayList<>();
        result.forEach(dataList::add);
        return dataList;
    }

    @GetMapping("/data/{id}")
    public Optional<Data> getData(@PathVariable String id)
    {
        Optional<Data> data = dataRepository.findById(Long.valueOf(id));
        return data;
    }
    @PutMapping("/data/{id}")
    public Optional<Data> updateData(@RequestBody Data newData, @PathVariable String id)
    {
        Optional<Data> optionalData = dataRepository.findById(Long.valueOf(id));
        if (optionalData.isPresent()) {
            Data data = optionalData.get();
            data.setTitle(newData.getTitle());
            data.setDescription(newData.getDescription());
            data.setAuthor(newData.getAuthor());
            data.setTags(newData.getTags());
            data.setCreated_At(newData.getCreated_At());
            data.setUpdated_At(newData.getUpdated_At());
            dataRepository.save(data);
        }
        return optionalData;
    }
    @DeleteMapping(value = "/data/{id}", produces = "application/json; charset=utf-8")
    public String deleteData(@PathVariable String id) {
        Boolean result = dataRepository.existsById(Long.valueOf(id));
        dataRepository.deleteById(Long.valueOf(id));
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }
    @PostMapping("/data")
    public String addData(@RequestBody Data newData)
    {
        Boolean result = dataRepository.existsById(Long.valueOf(newData.getId()));
        if(!result) {
            long id = Long.valueOf(newData.getId());
            Data data = new Data(id, newData.getTitle(), newData.getDescription(), newData.getAuthor(), newData.getTags(), newData.getCreated_At(), newData.getUpdated_At());
            dataRepository.save(data);
            return "Data sucessfully inserted";
        }
        return "Data with same id already present";
    }

}
