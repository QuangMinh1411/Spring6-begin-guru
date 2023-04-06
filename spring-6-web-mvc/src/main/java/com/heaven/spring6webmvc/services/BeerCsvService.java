package com.heaven.spring6webmvc.services;

import com.heaven.spring6webmvc.model.BeerCSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(File csvFile) throws FileNotFoundException;
}
