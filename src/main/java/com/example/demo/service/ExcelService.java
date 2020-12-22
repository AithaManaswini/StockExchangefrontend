package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.StockPrice;
import com.example.demo.helper.ExcelHelper;
import com.example.demo.repo.StockPriceRepository;

@Service
public class ExcelService {
  @Autowired
  StockPriceRepository repository;

  public void save(MultipartFile file) {
    try {
      List<StockPrice> sps = ExcelHelper.excelTosps(file.getInputStream());
      repository.saveAll(sps);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public List<StockPrice> getAllsps() {
    return repository.findAll();
  }
}
