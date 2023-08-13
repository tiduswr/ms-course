package com.tiduswr.hrworker.resources;

import com.tiduswr.hrworker.entities.Worker;
import com.tiduswr.hrworker.repository.WorkerRepository;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/workers")
public class WorkerResource {

    private static final Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @Value("${test.config}")
    private String testConfig;

    @Autowired
    Environment env;

    @Autowired
    private WorkerRepository repository;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Worker> findById(@PathVariable("id") Long id){
        if(logger.isInfoEnabled() && env != null) 
            logger.info("Solicitação recebida no worker da porta {}", 
                env.getProperty("local.server.port"));
        
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            logger.error("Erro no Thread.sleep(3000)", e);
        }

        Worker worker = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(worker);
    }

    @GetMapping("/configs")
    public ResponseEntity<Void> getConfigs(){

        if(logger.isInfoEnabled() && testConfig != null) 
            logger.info("Config= {}", testConfig);

        return ResponseEntity.noContent().build();
    }

}
