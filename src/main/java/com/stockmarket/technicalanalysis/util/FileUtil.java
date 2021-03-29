package com.stockmarket.technicalanalysis.util;

import com.stockmarket.technicalanalysis.constant.FileNameConstants;
import com.stockmarket.technicalanalysis.vo.Trade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;

@Slf4j
@Component
public class FileUtil {

   /* public void writeToFile(String fileContent, String fileName) throws Exception {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
            for (String str : fileContent) {
                writer.write(str + System.lineSeparator());
            }
        }catch (Exception e){
            log.error("Unable to write to fileName "+fileName, e);
            throw  new Exception("Unable to write to fileName "+fileName, e);
        }
        finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
*/
    private  String currentWorkingDirectory =  System.getProperty("user.dir");

    public void writeToFile(String fileContent, String fileName) throws Exception {
        FileOutputStream writer = null;
        try {
            File file = new File(System.getProperty("user.dir")+"\\"+ FileNameConstants.DOCUMENT_UPLOAD_DIRECTORY+"\\"+fileName);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                writer = new FileOutputStream(System.getProperty("user.dir")+"\\"+ FileNameConstants.DOCUMENT_UPLOAD_DIRECTORY+"\\"+fileName);
                writer.write(fileContent.getBytes());
            }
        }catch (Exception e){
            log.error("Unable to write to fileName "+fileName, e);
            throw  new Exception("Unable to write to fileName "+fileName, e);
        }
        finally {
            if(writer != null) {
                writer.close();
            }
        }
    }


    public void writeToFile(List<Trade> listOfTrade, String fileName) throws Exception {
        FileOutputStream writer = null;
        try {
            File file = new File(System.getProperty("user.dir")+"\\"+ FileNameConstants.DOCUMENT_UPLOAD_DIRECTORY+"\\"+fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
                writer = new FileOutputStream(System.getProperty("user.dir")+"\\"+ FileNameConstants.DOCUMENT_UPLOAD_DIRECTORY+"\\"+fileName);
            writer.write((new Trade().getCSVHeader()+System.lineSeparator()).getBytes() );
            for (Trade trade : listOfTrade) {
                writer.write((trade.toCSVWrite() + System.lineSeparator()).getBytes());
            }
        }catch (Exception e){
            log.error("Unable to write to fileName "+fileName, e);
            throw  new Exception("Unable to write to fileName "+fileName, e);
        }
        finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}
