package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IOUController {
    private IOUService iouService;

    @Autowired
    public IOUController(IOUService iouService) {
        this.iouService = iouService;
    }

    @GetMapping("/api/ious")
    public List<IOU> getAllIOUs() {
        return iouService.getAllIOUs();
    }

    @GetMapping("/api/ious/{id}")
    public ResponseEntity<IOU> getIOU(@PathVariable UUID id) {
        try {
            IOU getiou = iouService.getIOU(id);
            return ResponseEntity.ok(getiou);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/ious")
    public IOU createIou(@RequestBody IOU iou) {
        return iouService.createIOU(iou);
    }

    @PutMapping("/api/ious/{id}")
    public ResponseEntity<IOU> updateIOU(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
        try {
            IOU updatedIou = iouService.updateIOU(id, updatedIOU);
            return ResponseEntity.ok(updatedIou);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/api/ious/{id}")
    public ResponseEntity<Void> deleteIOU(@PathVariable UUID id){
        try {
            iouService.getIOU(id);
            iouService.deleteIOU(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException | NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
