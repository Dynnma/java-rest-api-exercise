package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class IOUService {
    public IOURepository iouRepository;

    public IOUService(IOURepository iouRepository) {
        this.iouRepository = iouRepository;
    }
    public List<IOU> getAllIOUs() {
        return iouRepository.findAll();
    }
    public IOU getIOU(UUID id) {
        return iouRepository.findById(id).orElseThrow();
    }
    public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
        return iouRepository.save(iou);
    }
    public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
        IOU iou = iouRepository.findById(id).orElseThrow();
        iou.setBorrower(updatedIOU.getBorrower());
        iou.setLender(updatedIOU.getLender());
        iou.setAmount(updatedIOU.getAmount());
        iou.setDateTime(updatedIOU.getDateTime());
        return iouRepository.save(iou);
    }
    public void deleteIOU(UUID id) {
        iouRepository.findById(id).orElseThrow();
        iouRepository.deleteById(id);
    }
}