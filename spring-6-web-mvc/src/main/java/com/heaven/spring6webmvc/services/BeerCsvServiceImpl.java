package com.heaven.spring6webmvc.services;

import com.heaven.spring6webmvc.model.BeerCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
@Service
public class BeerCsvServiceImpl implements BeerCsvService {
    @Override
    public List<BeerCSVRecord> convertCSV(File csvFile) throws FileNotFoundException {
        List<BeerCSVRecord> beerCSVRecords = new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(csvFile))
                .withType(BeerCSVRecord.class).build().parse();

        return beerCSVRecords;
    }
}
